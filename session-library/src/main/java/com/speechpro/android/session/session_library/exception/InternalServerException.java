package com.speechpro.android.session.session_library.exception;

/**
 * @author Alexander Grigal
 */
public class InternalServerException extends RestException {
    public InternalServerException(String message, String reason) {
        super(message, reason);
    }
}
