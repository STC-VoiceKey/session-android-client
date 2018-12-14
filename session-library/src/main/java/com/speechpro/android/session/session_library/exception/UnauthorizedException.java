package com.speechpro.android.session.session_library.exception;

/**
 * @author Alexander Grigal
 */
public class UnauthorizedException extends RestException {
    public UnauthorizedException(String message, String reason) {
        super(message, reason);
    }
}
