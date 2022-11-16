package net.ent.etrs.banque.model.entities.exceptions;

public class PersonnePrenomException extends Exception {
    public PersonnePrenomException(String message) {
        super(message);
    }

    public PersonnePrenomException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonnePrenomException(Throwable cause) {
        super(cause);
    }

    public PersonnePrenomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
