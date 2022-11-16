package net.ent.etrs.banque.model.daos;


import net.ent.etrs.banque.model.daos.exceptions.DaoException;
import net.ent.etrs.banque.model.entities.Client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Persistence memoire ( "bidon")
 *
 * @author christophe.cerqueira
 */

public class DaoBanqueEnMemoireImpl implements DaoBanque {

    /**
     * Attribut similant la persistance des clients.
     */
    private List<Client> persistance = new ArrayList<>();

    @Override
    public void create(Client c) throws DaoException {
//		if(c == null) {
//			throw new DaoException("Client non référencé");
//		}
        this.controlerClientNull(c);
        if (this.exist(c)) {
            throw new DaoException(String.format("Le client %s %s existe déjà", c.getNom(), c.getPrenom()));
        }

        boolean retourAdd = this.persistance.add(c);
        if (!retourAdd) {
//		if(!this.persistance.add(c)) {
            throw new DaoException(String.format("ERREUR INTERNE : Le client %s %s n'a pas été sauvé", c.getNom(), c.getPrenom()));
        }


    }

    @Override
    public void delete(Client c) throws DaoException {
        this.controlerClientNull(c);

        if (!this.exist(c)) {
            throw new DaoException(String.format("Le client %s %s n'existe pas", c.getNom(), c.getPrenom()));
        }

        if (!this.persistance.remove(c)) {
            throw new DaoException(String.format("ERREUR INTERNE : Le client %s %s n'existe pas", c.getNom(), c.getPrenom()));
        }
    }

    public boolean exist(Client c) {

        return this.persistance.contains(c);
    }

    @Override
    public Client read(UUID id) {
        Client c = null;
        if (id != null) {
            for (Client client : this.persistance) {
                if (client.getId().equals(id)) {
                    c = client;
                }
            }
        }


        return c;
    }

    @Override
    //Imposé par l'interface, je suis obligé de respecter la signature
    public List<Client> readAll() {

        return Collections.unmodifiableList(this.persistance);
    }

    @Override
    public void update(Client c) throws DaoException {
        this.controlerClientNull(c);
        if (!this.exist(c)) {
            throw new DaoException(String.format("Le client %s %s n'existe pas", c.getNom(), c.getPrenom()));
        }

        this.delete(c);
        this.create(c);
    }

    //En +, non imposé par l'interface
    private void controlerClientNull(Client client) throws DaoException {
        if (client == null) {
            throw new DaoException("Client non référencé");
        }

    }

}
