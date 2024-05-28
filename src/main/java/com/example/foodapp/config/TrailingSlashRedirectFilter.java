package com.example.foodapp.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.logging.Logger;

@Configuration
public class TrailingSlashRedirectFilter extends OncePerRequestFilter {

//    private static final Logger logger = LoggerFactory.getLogger(TrailingSlashRedirectFilter.class);

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        /* We want to obtain the complete request URL including the query string */
        String url = ServletUriComponentsBuilder.fromRequest(request).build().toUriString();
        String path = request.getRequestURI();
        String fixedUrl = "";

        if (url.endsWith("/") && path.length() > 1 /* not the root path */)
            fixedUrl = url.substring(0, url.length() - 1);

        if (path.isEmpty() /* root path without '/' */)
            fixedUrl = url + "/";

        if (!fixedUrl.isEmpty()) {
            response.setHeader(HttpHeaders.LOCATION, fixedUrl);
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
//            logger.trace("Redirecting with HttpStatus 301 for requested URL '{}' to '{}'", url, fixedUrl);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
