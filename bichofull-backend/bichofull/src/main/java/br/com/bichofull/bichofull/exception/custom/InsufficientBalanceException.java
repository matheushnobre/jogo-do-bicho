package br.com.bichofull.bichofull.exception.custom;

public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(){
        super("Balance must be greater than or equal to the bet amount");
    }

}
