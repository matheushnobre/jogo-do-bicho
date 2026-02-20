package br.com.bichofull.bichofull.exception;


public class FieldError {
    private String field;
    private String error;

    public FieldError(String field, String error) {
        this.field = field;
        this.error = error;
    }

    // Getters e Setters
    public String getField() { return field; }
    public String getError() { return error; }
}