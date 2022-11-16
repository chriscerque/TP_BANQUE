package net.ent.etrs.banque.model.entities.exceptions;

import net.ent.etrs.banque.model.entities.Client;
import net.ent.etrs.banque.model.entities.Compte;
import net.ent.etrs.banque.model.entities.references.ConstantesModel;

public class AjouterCompteClientException extends ClientException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public AjouterCompteClientException(Compte compte, Client client) {
        super(String.format(ConstantesModel.MSG_AJOUTER_COMPTE_CLIENT_EN_DOUBLE,
                compte.getId().toString(),
                compte.getTypeCompte().getLibelle(),
                client.getNom(),
                client.getPrenom()
        ));
    }


}
