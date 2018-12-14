package com.speechpro.android.session.session_library.exception;

import android.annotation.TargetApi;
import android.os.Build;

/**
 * @author Alexander Grigal
 */
public class RestException extends Exception {

    private String reason;

    public RestException(String reason) {
        this.reason = reason;
    }

    public RestException(String message, String reason) {
        super(message);
        this.reason = reason;
    }

    public RestException(String message, Throwable cause, String reason) {
        super(message, cause);
        this.reason = reason;
    }

    public RestException(Throwable cause, String reason) {
        super(cause);
        this.reason = reason;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public RestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String reason) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
