package net.ent.etrs.banque.model.entities.exceptions;

import net.ent.etrs.banque.model.entities.references.ConstantesModel;

import java.util.UUID;

public class CompteException extends Exception {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public CompteException(UUID idCompte) {
        super(String.format(ConstantesModel.COMPTE_EXCEPTION, idCompte.toString()));
    }

    public CompteException(String message) {
        super(message);
    }

    public CompteException(UUID idCompte, Throwable cause) {
        super(String.format(ConstantesModel.COMPTE_EXCEPTION, idCompte.toString()), cause);

    }


}
