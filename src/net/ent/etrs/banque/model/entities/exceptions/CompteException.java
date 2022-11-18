package net.ent.etrs.banque.model.entities.exceptions;

public class CompteException extends Exception {

    public CompteException(String message) {
        super(message);
    }

    public CompteException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompteException(Throwable cause) {
        super(cause);
    }

    public CompteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
