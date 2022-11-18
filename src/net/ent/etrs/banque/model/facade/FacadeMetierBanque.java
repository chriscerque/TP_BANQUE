package net.ent.etrs.banque.model.facade;

import net.ent.etrs.banque.model.entities.Client;
import net.ent.etrs.banque.model.entities.Compte;
import net.ent.etrs.banque.model.entities.exceptions.ClientException;
import net.ent.etrs.banque.model.entities.exceptions.CompteDecouvertAutoriseDepasseException;
import net.ent.etrs.banque.model.entities.exceptions.CompteException;
import net.ent.etrs.banque.model.entities.exceptions.CompteMontantNegatifException;
import net.ent.etrs.banque.model.facade.exceptions.BusinessException;

import java.util.List;

/**
 * Façade métier proposant les opérations utiles
 * à l'application.
 */
public interface FacadeMetierBanque {
    public void creerClient(Client client) throws ClientException;

    public void ajouterCompteClient(Client client, Compte compte) throws ClientException, CompteException;

    public List<Client> listerClient();

    public List<Compte> listerComptesClient(Client client);

    public void crediterCompte(Client client, Compte compte, Float montant) throws ClientException, CompteMontantNegatifException, CompteDecouvertAutoriseDepasseException;

    public void debiterCompte(Client client, Compte compte, Float montant) throws ClientException, CompteMontantNegatifException, CompteDecouvertAutoriseDepasseException;

    public void init() throws BusinessException;

}
