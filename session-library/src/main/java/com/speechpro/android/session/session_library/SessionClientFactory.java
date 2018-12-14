package com.speechpro.android.session.session_library;

import android.util.Pair;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speechpro.android.session.session_library.exception.BadRequestException;
import com.speechpro.android.session.session_library.exception.ForbiddenException;
import com.speechpro.android.session.session_library.exception.InternalServerException;
import com.speechpro.android.session.session_library.exception.InternetConnectionException;
import com.speechpro.android.session.session_library.exception.NotFoundException;
import com.speechpro.android.session.session_library.exception.RestException;
import com.speechpro.android.session.session_library.exception.ServiceUnavailableException;
import com.speechpro.android.session.session_library.exception.UnauthorizedException;
import com.speechpro.android.session.session_library.models.DomainResponse;
import com.speechpro.android.session.session_library.models.ErrorResponse;
import com.speechpro.android.session.session_library.models.OpenSessionRequest;
import com.speechpro.android.session.session_library.models.SessionIdResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Alexander Grigal
 */
public final class SessionClientFactory {

    private static final Map<Pair<String, Boolean>, SessionClient> store = new HashMap<>();

    public static final class SessionClient {

        private final SessionService mService;

        private SessionClient(String url, Boolean log) {
            OkHttpClient okHttpClient = getOkHttpClient(log);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .client(okHttpClient)
                    .build();

            mService = retrofit.create(SessionService.class);
        }

        /**
         * Gets domains.
         *
         * @return the domains
         * @throws IOException the io exception
         */
        public List<DomainResponse> getDomains() throws IOException {
            return mService.getDomains().execute().body();
        }

        /**
         * Close session.
         *
         * @param sessionId the session id
         * @return the boolean
         * @throws IOException the io exception
         */
        public boolean closeSession(String sessionId) throws IOException {
            return mService.closeSession(sessionId).execute().code() == 204;
        }


        /**
         * Open session.
         *
         * @param username
         * @param password
         * @param domain
         * @return sessionId
         * @throws RestException
         * @throws InternetConnectionException
         */
        public String openSession(String username, String password, Integer domain) throws RestException, InternetConnectionException {
            Response response = null;

            try {
                response = mService.openSession(new OpenSessionRequest(username, password, domain)).execute();
            } catch (IOException e) {
                throw new InternetConnectionException("Problem occurred talking to the server");
            }

            processCode(response);

            SessionIdResponse sessionIdResponse = (SessionIdResponse) response.body();

            return sessionIdResponse.getSessionId();
        }

        /**
         * Check session.
         *
         * @param sessionId the session id
         * @return the boolean
         * @throws IOException the io exception
         */
        public boolean checkSession(String sessionId) throws IOException {
            return mService.checkSession(sessionId).execute().body().getIsActive();
        }

        private <T> void processResponse(Call<T> call) throws RestException, InternetConnectionException {
            try {
                Response e = call.execute();
                this.processCode(e);
            } catch (IOException e) {
                throw new InternetConnectionException("Problem occurred talking to the server");
            }

        }

        private void processCode(Response response) throws RestException {
            int code = response.code();
            String message = null;
            String reason = null;

            try {
                ResponseBody error = response.errorBody();
                if (error != null) {
                    ErrorResponse errorResponse = convertJsonToObject(error.string());
                    if (errorResponse != null) {
                        message = errorResponse.message;
                        reason = errorResponse.reason;
                    } else {
                        message = response.raw().message();
                        reason = error.string();
                    }
                }
            } catch (IOException ignored) {
            }

            switch (code) {
                case 400:
                    throw new BadRequestException(message, reason);
                case 401:
                    throw new UnauthorizedException(message, reason);
                case 403:
                    throw new ForbiddenException(message, reason);
                case 404:
                    throw new NotFoundException(message, reason);
                case 500:
                    throw new InternalServerException(message, reason);
                case 503:
                    throw new ServiceUnavailableException(message, reason);
                default:
            }
        }

        private ErrorResponse convertJsonToObject(String jsonInString) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.readValue(jsonInString, ErrorResponse.class);
            } catch (IOException ignored) {
            }
            return null;
        }

        private OkHttpClient getOkHttpClient(boolean log) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (log) {
                builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            }

            return builder.build();
        }
    }

    /**
     *
     * @param url session URL
     * @param log log enabled or not
     * @return SessionClient
     */
    public static SessionClient get(String url, Boolean log) {
        synchronized (store) {
            SessionClient result = store.get(new Pair<>(url, log));
            if (result == null) {
                result = new SessionClient(url, log);
                store.put(new Pair<>(url, log), result);
            }
            return result;
        }
    }

}
