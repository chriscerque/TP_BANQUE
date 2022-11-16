package net.ent.etrs.banque.model.entities.exceptions;

import net.ent.etrs.banque.model.entities.references.ConstantesModel;

/**
 * Exception client typé pour la création d'un client.
 *
 * @author christophe.cerqueira
 */
public class CreationClientException extends ClientException {


    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public CreationClientException() {
        super(ConstantesModel.MSG_CREATION_CLIENT);
    }


}
