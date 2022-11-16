package net.ent.etrs.banque.model.daos;

import net.ent.etrs.banque.model.daos.exceptions.DaoException;
import net.ent.etrs.banque.model.entities.Client;

import java.util.List;
import java.util.UUID;

public interface DaoBanque {
    void create(Client c) throws DaoException;

    void delete(Client c) throws DaoException;

    Client read(UUID id);

    //Imposé par l'interface, je suis obligé de respecter la signature
    List<Client> readAll();

    void update(Client c) throws DaoException;
}
