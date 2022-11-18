package net.ent.etrs.banque.model.facade;


import net.ent.etrs.banque.model.daos.DaoBanque;
import net.ent.etrs.banque.model.daos.DaoFactory;
import net.ent.etrs.banque.model.daos.exceptions.DaoException;
import net.ent.etrs.banque.model.entities.Client;
import net.ent.etrs.banque.model.entities.Compte;
import net.ent.etrs.banque.model.entities.EntitiesFactory;
import net.ent.etrs.banque.model.entities.exceptions.*;
import net.ent.etrs.banque.model.entities.references.ConstantesModel;
import net.ent.etrs.banque.model.entities.references.TypeCompte;
import net.ent.etrs.banque.model.facade.exceptions.BusinessException;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class FacadeMetierImpl implements FacadeMetierBanque {

    private DaoBanque banqueDao = DaoFactory.persistenceMemoireFactory();

    protected FacadeMetierImpl() {
    }

    @Override
    public void creerClient(Client client) throws ClientException {
        try {
            this.banqueDao.create(client);
        } catch (DaoException e) {
            throw new ClientException(String.format(ConstantesModel.CLIENT_CREATION_EXCEPTION, client.getNom(), client.getPrenom()));
        }

    }

    @Override
    public void ajouterCompteClient(Client client, Compte compte) throws ClientException, CompteException {
        if (client == null) {
            throw new ClientException("Client non référencé");
        }
        if (compte == null) {
            throw new CompteException("Compte non référencé");
        }

        client.ajouterCompte(compte);

        try {
            this.banqueDao.update(client);
        } catch (DaoException e) {
            throw new ClientException(String.format("Problème lors de l'ajout du compte %s sur le client %s %s",
                    compte.getId(), client.getNom(), client.getPrenom()));
        }

    }

    @Override
    public List<Client> listerClient() {
        return this.banqueDao.readAll();
    }

    @Override
    public List<Compte> listerComptesClient(Client client) {
        Client c = this.banqueDao.read(client.getId());
        return c.getComptes();
    }

    @Override
    public void crediterCompte(Client client, Compte compte, Float montant) throws ClientException, CompteMontantNegatifException, CompteDecouvertAutoriseDepasseException {
        // contrôle que le compte est bien associé au client
        if (!client.getComptes().contains(compte)) {
            throw new ClientException(String.format("Le compte %s n'est pas associé au client %s %s",
                    compte.getId(), client.getNom(), client.getPrenom()));
        }
        // je crédite le compte
        compte.crediter(montant);

        // je fais la mise à jour en persistance
        try {
            this.banqueDao.update(client);
        } catch (DaoException e) {
            throw new ClientException(String.format("Problème lors du crédit du compte %s du client %s %s",
                    compte.getId(), client.getNom(), client.getPrenom()));
        }

    }

    @Override
    public void debiterCompte(Client client, Compte compte, Float montant) throws ClientException, CompteMontantNegatifException, CompteDecouvertAutoriseDepasseException {
        // contrôle que le compte est bien associé au client
        if (!client.getComptes().contains(compte)) {
            throw new ClientException(String.format("Le compte %s n'est pas associé au client %s %s",
                    compte.getId(), client.getNom(), client.getPrenom()));
        }
        // je crédite le compte
        compte.debiter(montant);

        // je fais la mise à jour en persistance
        try {
            this.banqueDao.update(client);
        } catch (DaoException e) {
            throw new ClientException(String.format("Problème lors du crédit du compte %s du client %s %s",
                    compte.getId(), client.getNom(), client.getPrenom()));
        }

    }

    @Override
    public void init() throws BusinessException {
        this.creerClientAvecComptes("Le Cam", "Jean", LocalDate.of(1969, 01, 10));
        this.creerClientAvecComptes("Le Cleac'h", "Armel", LocalDate.of(1980, 02, 01));
        this.creerClientAvecComptes("Thomson", "Alex", LocalDate.of(1990, 03, 01));

    }


    private void creerClientAvecComptes(String nom, String prenom, LocalDate dateNaissance) throws BusinessException {

        Client client;
        Random random = new Random();

        try {
            client = EntitiesFactory.fabriquerClient(nom, prenom, dateNaissance);
            // creation du compte courant
            Compte compte1 = EntitiesFactory.fabriquerCompte(client, TypeCompte.COMPTE_COURANT, 1000.0f);
            compte1.crediter(1000 * random.nextFloat());
            // création du livret A
            Compte compte2 = EntitiesFactory.fabriquerCompte(client, TypeCompte.LIVRET_A, 50.0f);
            compte2.crediter(1000 * random.nextFloat());
            // création du livret dvd
            Compte compte3 = EntitiesFactory.fabriquerCompte(client, TypeCompte.LIVRET_DVD, 0.0f);
            compte3.crediter(1000 * random.nextFloat());

            // ajout des 3 comptes au client
            client.ajouterCompte(compte1);
            client.ajouterCompte(compte2);
            client.ajouterCompte(compte3);

            // persiter
            this.banqueDao.create(client);

        } catch (CompteConstructionException | ClientException | DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }


    }

}
