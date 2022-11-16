package net.ent.etrs.banque.model.entities.exceptions;

import net.ent.etrs.banque.model.entities.references.ConstantesModel;

import java.util.UUID;

public class CompteDecouvertAutoriseDepasseException extends CompteException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public CompteDecouvertAutoriseDepasseException(UUID idCompte) {
        super(String.format(ConstantesModel.COMPTE_DECOUVERT_AUTORISE_DEPASSE_EXCEPTION, idCompte.toString()));
    }

}
