package br.com.bichofull.bichofull.exception.custom;

public class EmailAlreadyRegisteredException extends RuntimeException {

    public EmailAlreadyRegisteredException(String email){
        super("Email already registered: " + email);
    }
}
