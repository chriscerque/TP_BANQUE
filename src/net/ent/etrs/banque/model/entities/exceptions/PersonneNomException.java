package net.ent.etrs.banque.model.entities.exceptions;

public class PersonneNomException extends Exception {
    public PersonneNomException(String message) {
        super(message);
    }

    public PersonneNomException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonneNomException(Throwable cause) {
        super(cause);
    }

    public PersonneNomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
