package net.ent.etrs.banque.model.entities.exceptions;


import net.ent.etrs.banque.model.entities.references.ConstantesModel;

public class DateNaissanceClientErroneeException extends ClientException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public DateNaissanceClientErroneeException(String nom, String prenom) {
        super(String.format(ConstantesModel.MSG_DATE_NAISSANCE_CLIENT_ERRONEE,
                nom, prenom));
    }


}
