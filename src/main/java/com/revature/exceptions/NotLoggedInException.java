package com.revature.exceptions;

public class NotLoggedInException extends AuthorizationException {  // <-- this is the exceptions class
    public NotLoggedInException() { // <-- this is the constructor
        super();
    }
    public NotLoggedInException(String message) {
        super(message);
    }
    public NotLoggedInException(Throwable cause) {
        super(cause);
    }
    public NotLoggedInException(String message, Throwable cause) {
        super(message, cause);
    }
    public NotLoggedInException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
