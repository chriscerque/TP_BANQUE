package net.ent.etrs.banque.presenter;

import net.ent.etrs.banque.model.entities.Client;
import net.ent.etrs.banque.model.entities.Compte;
import net.ent.etrs.banque.model.entities.exceptions.*;
import net.ent.etrs.banque.model.facade.FacadeMetierBanque;
import net.ent.etrs.banque.view.console.ViewBanque;

import java.math.BigDecimal;
import java.util.List;

/**
 * Classe représentant le PRESENTER de l'application
 * selon le paradigme MVP.
 *
 * @author Christophe LOUËR
 */
public class BanquePresenter {
    private ViewBanque vue;
    private FacadeMetierBanque metier;

    public BanquePresenter(ViewBanque vue, FacadeMetierBanque metier) {
        super();
        this.vue = vue;
        this.metier = metier;
    }

    /**
     * Methode exécutant le menu de l'application.
     */
    public void execute() {
        int choix = 0;
        do {
            choix = vue.afficherMenu();
            traiterMenu(choix);

        } while (choix != 0);

    }

    /**
     * Méthode execitant le choix saisi.
     *
     * @param choix
     */
    private void traiterMenu(int choix) {
        switch (choix) {
            case 1:    // 1. Liste des clients
                listerClient();
                break;

            case 2:    //2. Créer un nouveau client
                creerClient();
                break;

            case 3:    //3. Supprimer un client
                supprimerClient();
                break;

            case 4:    // 4. Ajouter un compte client
                ajouterCompteClient();
                break;

            case 5:    //5. Crediter un compte
                crediterCompte();
                break;

            case 6:    //6.Debiter un compte
                debiterCompte();
                break;

            case 7:    //7. Afficher un client
                afficherUnClient();
                break;
            default:

        }
    }

    //Menu 1
    private void listerClient() {
        List<Client> clients = metier.listerClient();
        vue.afficherClients(clients);

    }

    //Menu 2
    private void creerClient() {

        try {
            //Je demande à la vue la saisie des caractéristiques d'un nouveau client
            Client nouveauClient = vue.saisirClient();
            //Puis je demande au metier de le persister
            metier.creerClient(nouveauClient);
        } catch (DateNaissanceClientErroneeException e) {
            //je demande a la vue d'afficher le msg de l'exception
            vue.afficherMessageErreur(e.getMessage());
        } catch (ClientException e) {
            vue.afficherMessageErreur("Impossible de crréer le nouveau client contacter le formateur");
        }

    }

    //Menu 3
    private void supprimerClient() {
        //Demander les clients au metier
        List<Client> lesClients = metier.listerClient();
        //Selectionner le client
        Client clientSelect = vue.selectionnerClient(lesClients);
        //Supprimer le client
        //TODO
        //metier.supprimerClient(clientSelect);

    }

    //Menu 4
    private void ajouterCompteClient() {
        List<Client> clients = metier.listerClient();
        Client clientChoisi = vue.selectionnerClient(clients);

        Compte nouveauCompte = vue.saisirCompte(clientChoisi);


        try {
            metier.ajouterCompteClient(clientChoisi, nouveauCompte);
        } catch (ClientException | CompteException e) {
            vue.afficherMessageErreur(e.getMessage());
            //System.out.println(e.getMessage());
        }
    }


    //Menu 5
    private void crediterCompte() {
        //Demander les clients au metier
        List<Client> lesClients = metier.listerClient();
        //Selectionner le client
        Client clientSelect = vue.selectionnerClient(lesClients);
        //Selectionner le compte
        Compte compteSelect = vue.selectionnerCompte(clientSelect);
        //selectionner le montant
        BigDecimal montantSelect = vue.saisirMontant("Choisir le montant:");
        try {
            metier.crediterCompte(clientSelect, compteSelect, montantSelect);
        } catch (CompteMontantNegatifException | CompteDecouvertAutoriseDepasseException | ClientException e) {
            vue.afficherMessageErreur(e.getMessage());
        }
    }

    //Menu 6
    private void debiterCompte() {
        //Demander les clients au metier
        List<Client> lesClients = metier.listerClient();
        //Selectionner le client
        Client clientSelect = vue.selectionnerClient(lesClients);
        //Selectionner le compte
        Compte compteSelect = vue.selectionnerCompte(clientSelect);
        //selectionner le montant
        BigDecimal montantSelect = vue.saisirMontant("Choisir le montant:");
        try {
            metier.debiterCompte(clientSelect, compteSelect, montantSelect);
        } catch (CompteMontantNegatifException | CompteDecouvertAutoriseDepasseException | ClientException e) {
            vue.afficherMessageErreur(e.getMessage());
        }
    }

    //Menu 7
    private void afficherUnClient() {
        List<Client> clients = metier.listerClient();
        Client clientChoisi = vue.selectionnerClient(clients);
        vue.afficherUnClient(clientChoisi);
    }


}
