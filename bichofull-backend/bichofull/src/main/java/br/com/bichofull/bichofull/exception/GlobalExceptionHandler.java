package br.com.bichofull.bichofull.exception;

import br.com.bichofull.bichofull.exception.custom.EmailAlreadyRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation errors thrown when request body fields fail constraints.
     * This method catches MethodArgumentNotValidException, extracts the field-specific
     * validation errors, and returns a structured response with HTTP status 422 (Unprocessable Entity).
     *
     * @param ex the exception containing validation error details
     * @return ResponseEntity containing an ApiError object with error status, message, and field errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new FieldError(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        ApiError apiError = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation Error", fieldErrors);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(apiError);
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<ApiError> handleEmailAlreadyRegistered(EmailAlreadyRegisteredException exception){
        ApiError apiError = new ApiError(HttpStatus.CONFLICT.value(), exception.getMessage(), List.of());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
    }
}