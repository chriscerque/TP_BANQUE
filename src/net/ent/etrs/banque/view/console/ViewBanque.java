package net.ent.etrs.banque.view.console;

import net.ent.etrs.banque.model.entities.Client;
import net.ent.etrs.banque.model.entities.Compte;
import net.ent.etrs.banque.model.entities.exceptions.DateNaissanceClientErroneeException;
import net.ent.etrs.banque.model.entities.references.TypeCompte;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface regroupant toutes les opérations utiles
 * aux vues consoles.
 *
 * @author Christophe LOUËR
 */
public interface ViewBanque {

    /**
     * Affiche le menu de l'application et attend
     * la saisie du choix de l'utilisateur.
     * Le choix saisi est contrôlé.
     *
     * @return Le choix valide saisi par l'utilisateur.
     */
    public int afficherMenu();

    public Client saisirClient() throws DateNaissanceClientErroneeException;

    public void afficherMessageErreur(String message);

    /**
     * Affiche sur la console la liste des clients de la banque.
     *
     * @param clients Liste des clilents de la banque.
     */
    public void afficherClients(List<Client> clients);

    public void afficherUnClient(Client client);

    public Client selectionnerClient(List<Client> clients);

    public Compte selectionnerCompte(Client client);

    public TypeCompte selectionnerTypeCompte();

    public BigDecimal saisirMontant(String montant);

    public Compte saisirCompte(Client client);

}
