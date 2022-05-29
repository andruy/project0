package com.revature.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import com.revature.models.Role;


@Retention(RUNTIME) // This annotation will be available at runtime
@Target(METHOD) // This annotation will only be available on methods
public @interface Authorized {

        public Role[] allowedRoles() default {}; // default is an array of Role.values()
        // This annotation will have an allowedRoles field that is of type Role[]
        // If this annotation is not provided a value for this field, it will have
        // an empty array
}
