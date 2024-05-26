package me.alexander.expensetracker.service.interceptors;

import com.auth0.jwt.exceptions.JWTVerificationException;
import me.alexander.expensetracker.model.api.ApiError;
import me.alexander.expensetracker.model.api.ApiSubError;
import me.alexander.expensetracker.model.api.ApiValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        String objectName = ex.getObjectName();
        String cutObjectName = objectName.substring(0, objectName.indexOf("DTO"));

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, cutObjectName, ex);

        List<ApiSubError> apiSubErrors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            ApiSubError apiSubError = new ApiValidationError(fieldName, errorMessage);
            apiSubErrors.add(apiSubError);
        });

        apiError.setSubErrors(apiSubErrors);

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> handleAuthenticationException(Exception ex) {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, "Wrong email or password", ex);

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(AccountStatusException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> handleAccountStatusException(AccountStatusException ex) {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, "User account is abnormal", ex);

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(JWTVerificationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ResponseEntity<Object> handleTokenVerificationException(JWTVerificationException ex) {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, "The access token provided is expired, revoked, malformed, or invalid for other reasons.", ex);

        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
