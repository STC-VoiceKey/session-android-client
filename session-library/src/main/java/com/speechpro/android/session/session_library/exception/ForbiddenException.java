package com.speechpro.android.session.session_library.exception;

/**
 * @author Alexander Grigal
 */
public class ForbiddenException extends RestException {
    public ForbiddenException(String message, String reason) {
        super(message, reason);
    }
}
