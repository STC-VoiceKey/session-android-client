package com.speechpro.android.session.session_library;

import com.speechpro.android.session.session_library.models.CheckSessionResponse;
import com.speechpro.android.session.session_library.models.DomainResponse;
import com.speechpro.android.session.session_library.models.OpenSessionRequest;
import com.speechpro.android.session.session_library.models.SessionIdResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * @author Alexander Grigal
 */
public interface SessionService {

    @GET("domains")
    Call<List<DomainResponse>> getDomains();

    @DELETE("session")
    Call<Void> closeSession(@Header("X-Session-ID") String sessionId);

    @GET("session")
    Call<CheckSessionResponse> checkSession(@Header("X-Session-ID") String sessionId);

    @POST("session")
    Call<SessionIdResponse> openSession(@Body OpenSessionRequest request);

}
