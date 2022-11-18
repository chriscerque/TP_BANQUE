package net.ent.etrs.banque.presenter;

import net.ent.etrs.banque.commons.AffichageConsole;
import net.ent.etrs.banque.commons.LectureConsole;
import net.ent.etrs.banque.model.entities.Client;
import net.ent.etrs.banque.model.entities.Compte;
import net.ent.etrs.banque.model.entities.EntitiesFactory;
import net.ent.etrs.banque.model.entities.exceptions.*;
import net.ent.etrs.banque.model.entities.references.TypeCompte;
import net.ent.etrs.banque.model.facade.FacadeMetierBanque;
import net.ent.etrs.banque.presenter.references.ConstantesPresenter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant le PRESENTER de l'application
 * selon le paradigme MVP.
 */
public class Presenter {

    private FacadeMetierBanque metier;

    public Presenter(FacadeMetierBanque metier) {
        ;
        this.metier = metier;
    }

    private static int afficherMenu() {
        // affichage du menu
        for (String option : ConstantesPresenter.MENU_BANQUE) {
            // afficher sur la console les options du menu.
            AffichageConsole.afficherMessageAvecSautLigne(option);
        }

        // Lire le choix saisi par l'utilisateur.

        return LectureConsole.lectureChoixInt(0, ConstantesPresenter.PLUS_GRANDE_OPTION_MENU);
    }

    private static void afficherClients(List<Client> clients) {
        for (Client client : clients) {
            AffichageConsole.afficherMessageAvecSautLigne(client.toString());
        }
    }

    private static Client saisirClient() throws DateNaissanceClientErroneeException {
        //Etape1: faire saisir un nom, un prenom, une date de naissance pour le nouveau client à créer
        String nom = LectureConsole.lectureChaineCaracteres("Nom du nouveau client ?");

        String prenom = LectureConsole.lectureChaineCaracteres("Prenom du nouveau client ?");

        LocalDate dateNaissance = LectureConsole.lectureLocalDate("Date de naissance du nouveau client ?", "dd/MM/yyyy");

        //Etape 2 : demander une instance de client à la clientFactory

        return EntitiesFactory.fabriquerClient(nom, prenom, dateNaissance);

    }

    private static Client selectionnerClient(List<Client> clients) {
//		AffichageConsole.afficherMessageAvecSautLigne("Selectionner un client parmi la liste:");
//		for (int i = 0; i < clients.size(); i++) {
//			AffichageConsole.afficherMessageAvecSautLigne(String.format("%d - %s%n",i+1,clients.get(i).getNom()));
//		}
//		int select= LectureConsole.lectureChoixInt(1, clients.size());
//		return clients.get(select-1);

        // FORMATAGE DE LA LISTE A AFFICHER
        List<String> liste = new ArrayList<>();
        for (Client client : clients) {
            liste.add(String.format("%s %s", client.getNom(), client.getPrenom()));
        }

        AffichageConsole.afficherMenuSimple(liste);

        int select = LectureConsole.lectureChoixInt(0, liste.size());
        return clients.get(select - 1);


    }

    private static Compte saisirCompte(Client client) throws CompteConstructionException {
        // Afficher un menu listant les types de compte.

        List<String> listeTypeCompte = new ArrayList<>();

        TypeCompte[] listeTypes = TypeCompte.values();
        for (TypeCompte type : listeTypes) {
            listeTypeCompte.add(type.getLibelle());
        }

        AffichageConsole.afficherMenuSimpleAvecOptionSortie(listeTypeCompte, "Sortir");

        // lecture du choix de l'utilisateur
        int choix = LectureConsole.lectureChoixInt(0, listeTypeCompte.size());

        // instanciation du compte
        Compte compte = EntitiesFactory.fabriquerCompte(client, listeTypes[choix - 1], 0.0f);

        return compte;
    }

    private static Compte selectionnerCompte(Client client) {
        AffichageConsole.afficherMessageAvecSautLigne("Selectionner un compte parmi la liste:");
        for (int i = 0; i < client.getComptes().size(); i++) {
            AffichageConsole.afficherMessageAvecSautLigne(String.format("%d - %s%n", i + 1, client.getComptes().get(i)));
        }
        int select = LectureConsole.lectureChoixInt(1, client.getComptes().size());
        return client.getComptes().get(select - 1);
    }

    private static Float saisirMontant(String montant) {
        return ((Double) LectureConsole.lectureDouble("Saisir le montant:")).floatValue();
    }

    private static void afficherUnClient(Client client) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("CLIENT n° %s", client.getId().toString()));
        builder.append(String.format(" %-10s %-10s né le %s", client.getNom(), client.getPrenom(), client.getDateNaissance()));

        builder.append(client);
        builder.append(System.lineSeparator());
        builder.append("\tLISTE DES COMPTES :");
        for (Compte compte : client.getComptes()) {
            builder.append(System.lineSeparator());
            builder.append(compte);
        }
        builder.append(System.lineSeparator());

        AffichageConsole.afficherMessageAvecSautLigne(builder.toString());
    }

    /**
     * Methode exécutant le menu de l'application.
     */
    public void execute() {
        int choix = 0;
        do {
            choix = Presenter.afficherMenu();
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
        Presenter.afficherClients(clients);

    }

    //Menu 2
    private void creerClient() {

        try {
            //Je demande à la vue la saisie des caractéristiques d'un nouveau client
            Client nouveauClient = Presenter.saisirClient();
            //Puis je demande au metier de le persister
            metier.creerClient(nouveauClient);
        } catch (DateNaissanceClientErroneeException e) {
            //je demande a la vue d'afficher le msg de l'exception
            AffichageConsole.afficherErreur(e.getMessage());
        } catch (ClientException e) {
            AffichageConsole.afficherErreur("Impossible de crréer le nouveau client contacter le formateur");
        }

    }

    //Menu 3
    private void supprimerClient() {
        //Demander les clients au metier
        List<Client> lesClients = metier.listerClient();
        //Selectionner le client
        Client clientSelect = Presenter.selectionnerClient(lesClients);
        //Supprimer le client
        //TODO
        //metier.supprimerClient(clientSelect);

    }

    //Menu 4
    private void ajouterCompteClient() {
        try {
            List<Client> clients = metier.listerClient();
            Client clientChoisi = Presenter.selectionnerClient(clients);

            Compte nouveauCompte = Presenter.saisirCompte(clientChoisi);

            metier.ajouterCompteClient(clientChoisi, nouveauCompte);
        } catch (ClientException | CompteException | CompteConstructionException e) {
            AffichageConsole.afficherErreur(e.getMessage());
        }
    }

    //Menu 5
    private void crediterCompte() {
        //Demander les clients au metier
        List<Client> lesClients = metier.listerClient();
        //Selectionner le client
        Client clientSelect = Presenter.selectionnerClient(lesClients);
        //Selectionner le compte
        Compte compteSelect = Presenter.selectionnerCompte(clientSelect);
        //selectionner le montant
        Float montantSelect = Presenter.saisirMontant("Choisir le montant:");
        try {
            metier.crediterCompte(clientSelect, compteSelect, montantSelect);
        } catch (CompteMontantNegatifException | CompteDecouvertAutoriseDepasseException | ClientException e) {
            AffichageConsole.afficherErreur(e.getMessage());
        }
    }

    //Menu 6
    private void debiterCompte() {
        //Demander les clients au metier
        List<Client> lesClients = metier.listerClient();
        //Selectionner le client
        Client clientSelect = Presenter.selectionnerClient(lesClients);
        //Selectionner le compte
        Compte compteSelect = Presenter.selectionnerCompte(clientSelect);
        //selectionner le montant
        Float montantSelect = Presenter.saisirMontant("Choisir le montant:");
        try {
            metier.debiterCompte(clientSelect, compteSelect, montantSelect);
        } catch (CompteMontantNegatifException | CompteDecouvertAutoriseDepasseException | ClientException e) {
            AffichageConsole.afficherErreur(e.getMessage());
        }
    }

    //Menu 7
    private void afficherUnClient() {
        List<Client> clients = metier.listerClient();
        Client clientChoisi = Presenter.selectionnerClient(clients);
        Presenter.afficherUnClient(clientChoisi);
    }

}
