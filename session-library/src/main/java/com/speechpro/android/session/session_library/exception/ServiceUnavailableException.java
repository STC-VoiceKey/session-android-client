package com.speechpro.android.session.session_library.exception;

/**
 * @author Alexander Grigal
 */
public class ServiceUnavailableException extends RestException {
    public ServiceUnavailableException(String message, String reason) {
        super(message, reason);
    }
}
