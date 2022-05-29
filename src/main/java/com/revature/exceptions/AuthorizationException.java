package com.revature.exceptions;

public abstract class AuthorizationException extends RuntimeException { // This is an abstract class, so it can't be instantiated.
    //  It's only used as a parent class for other exceptions.  It's not a class that can be instantiated. It's a parent class.

    protected AuthorizationException() { // This is the default constructor.
        super();  // This is the super constructor.
    }

    protected AuthorizationException(String message) {  // This is the constructor that takes in a String.
        super(message); // This is the super constructor that takes in a String.
    }

    protected AuthorizationException(Throwable cause) { // This is the constructor that takes in a Throwable.
        super(cause);   // This is the super constructor that takes in a Throwable.
    }

    protected AuthorizationException(String message, Throwable cause) { // This is the constructor that takes in a String and a Throwable.
        super(message, cause);  // This is the super constructor that takes in a String and a Throwable.
    }

    protected AuthorizationException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {   // This is the constructor that takes in a String, a Throwable, a boolean, and a boolean.
        super(message, cause, enableSuppression, writableStackTrace);   // This is the super constructor that takes in a String, a Throwable, a boolean, and a boolean.
    }

}
