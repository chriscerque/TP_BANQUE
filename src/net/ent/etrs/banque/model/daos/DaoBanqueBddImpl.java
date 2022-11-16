package net.ent.etrs.banque.model.daos;


import net.ent.etrs.banque.model.daos.exceptions.DaoException;
import net.ent.etrs.banque.model.entities.Client;

import java.util.List;
import java.util.UUID;

/**
 * Persistence base de données
 *
 * @author christophe.cerqueira
 */
public class DaoBanqueBddImpl implements DaoBanque {

    @Override
    public void create(Client t) throws DaoException {
        //requete SQL : INSERT....
    }

    @Override
    public void delete(Client t) throws DaoException {
        // TODO Auto-generated method stub

    }


    public boolean exist(Client t) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Client read(UUID k) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Client> readAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Client t) throws DaoException {
        // TODO Auto-generated method stub

    }

}
