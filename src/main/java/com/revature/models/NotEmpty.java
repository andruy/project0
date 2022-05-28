package com.revature.models;

public @interface NotEmpty {
    String message( ) default "Please enter a valid email";
}
