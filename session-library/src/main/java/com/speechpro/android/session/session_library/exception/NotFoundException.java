package com.speechpro.android.session.session_library.exception;

/**
 * @author Alexander Grigal
 */
public class NotFoundException extends RestException {
    public NotFoundException(String message, String reason) {
        super(message, reason);
    }
}
