package net.ent.etrs.banque.model.entities.exceptions;

public class CompteTypeCompteException extends Exception {
    public CompteTypeCompteException(String message) {
        super(message);
    }

    public CompteTypeCompteException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompteTypeCompteException(Throwable cause) {
        super(cause);
    }

    public CompteTypeCompteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
