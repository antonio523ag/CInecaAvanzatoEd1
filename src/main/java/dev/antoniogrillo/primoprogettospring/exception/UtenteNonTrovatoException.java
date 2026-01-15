package dev.antoniogrillo.primoprogettospring.exception;

public class UtenteNonTrovatoException extends RuntimeException{
    public UtenteNonTrovatoException(String message) {
        super(message);
    }
}
