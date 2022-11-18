package net.ent.etrs.banque.model.entities.exceptions;

public class CompteConstructionException extends Exception {
    public CompteConstructionException(String message) {
        super(message);
    }

    public CompteConstructionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompteConstructionException(Throwable cause) {
        super(cause);
    }

    public CompteConstructionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
