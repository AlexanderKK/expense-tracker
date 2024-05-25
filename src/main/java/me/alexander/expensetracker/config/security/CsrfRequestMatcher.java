package me.alexander.expensetracker.config.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.regex.Pattern;

public class CsrfRequestMatcher implements RequestMatcher {

    private final Pattern allowedMethods = Pattern.compile("^GET|POST$");

    @Override
    public boolean matches(HttpServletRequest request) {
        return !allowedMethods.matcher(request.getMethod()).matches();
    }

    public boolean buildMatcher(HttpServletRequest request) {
        return this.matcher(request).isMatch();
    }

}
