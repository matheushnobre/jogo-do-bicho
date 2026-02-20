package br.com.bichofull.bichofull.exception;

import java.util.List;

public class ApiError {
    private int status;
    private String message;
    private List<FieldError> errors;

    public ApiError(int status, String message, List<FieldError> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    // Getters e Setters
    public int getStatus() { return status; }
    public String getMessage() { return message; }
    public List<FieldError> getErrors() { return errors; }
}