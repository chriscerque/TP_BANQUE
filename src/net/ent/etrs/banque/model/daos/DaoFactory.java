package net.ent.etrs.banque.model.daos;


/**
 * Fabrique de persistance
 *
 * @author christophe.cerqueira
 */
public final class DaoFactory {

    //Empeche l'instanciation
    private DaoFactory() {
    }

    /**
     * Méthode statique qui m'instancie une persistence memoire de client
     */
    public static DaoBanque persistenceMemoireFactory() {
        return new DaoBanqueEnMemoireImpl();
    }

    /**
     * Méthode statique qui m'instancie une persistence base de données de client
     */
    public static DaoBanque persistenceBddFactory() {
        return new DaoBanqueBddImpl();
    }
}
