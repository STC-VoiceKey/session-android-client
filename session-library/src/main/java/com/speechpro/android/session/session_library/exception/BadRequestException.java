package com.speechpro.android.session.session_library.exception;

/**
 * @author Alexander Grigal
 */
public class BadRequestException extends RestException {
    public BadRequestException(String message, String reason) {
        super(message, reason);
    }
}
