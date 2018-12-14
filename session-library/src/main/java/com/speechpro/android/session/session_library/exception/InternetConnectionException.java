package com.speechpro.android.session.session_library.exception;

import android.annotation.TargetApi;
import android.os.Build;

/**
 * @author Alexander Grigal
 */
public class InternetConnectionException extends Exception {

    public InternetConnectionException() {
    }

    public InternetConnectionException(String message) {
        super(message);
    }

    public InternetConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternetConnectionException(Throwable cause) {
        super(cause);
    }

    @TargetApi(Build.VERSION_CODES.N)
    public InternetConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
