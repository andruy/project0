package com.revature.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class DurationFilter extends OncePerRequestFilter { // OncePerRequestFilter is a filter that runs only
    // once per request...It's a singleton, so it can be used in multiple places.

    private static final Logger log = LoggerFactory.getLogger(DurationFilter.class);
    // Logger is a class that is used to log messages.

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        // doFilterInternal is a method that is called by the filter chain.
            throws ServletException, IOException {

        long startTime = System.currentTimeMillis();    // System.currentTimeMillis() returns the current time in milliseconds.

        try {

            MDC.put("requestId", UUID.randomUUID().toString()); // UUID is a class that generates a random UUID.
            // MDC is a class that is used to store key/value pairs.

            // Continue on to the next filter
            filterChain.doFilter(request, response);
        } finally {

            long endTime = System.currentTimeMillis();
            String duration = String.format("%d", endTime - startTime); // String.format() is a method that formats a string.

            MDC.put("duration", duration);

            log.info("Request has finished processing and took {} ms", duration);
            // Logger.info() is a method that logs an info message.

            MDC.clear();    // MDC.clear() is a method that clears the MDC.
        }
    }
}
