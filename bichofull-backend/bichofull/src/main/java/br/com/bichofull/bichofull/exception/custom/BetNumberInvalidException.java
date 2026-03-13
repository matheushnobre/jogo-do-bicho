package br.com.bichofull.bichofull.exception.custom;

public class BetNumberInvalidException extends RuntimeException{

    public BetNumberInvalidException(){
        super("Invalid bet number");
    }

}
