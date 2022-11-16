package net.ent.etrs.banque.model.entities.exceptions;

import net.ent.etrs.banque.model.entities.references.ConstantesModel;

import java.util.UUID;

public class CompteMontantNegatifException extends CompteException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public CompteMontantNegatifException(UUID idCompte) {
        super(String.format(ConstantesModel.COMPTE_MONTANT_NEGATIF_EXCEPTION, idCompte.toString()));

    }


}
