package net.ent.etrs.banque.view.console;

import net.ent.etrs.banque.commons.AffichageConsole;
import net.ent.etrs.banque.commons.LectureConsole;
import net.ent.etrs.banque.model.entities.Client;
import net.ent.etrs.banque.model.entities.Compte;
import net.ent.etrs.banque.model.entities.EntitiesFactory;
import net.ent.etrs.banque.model.entities.exceptions.DateNaissanceClientErroneeException;
import net.ent.etrs.banque.model.entities.references.TypeCompte;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ViewBanqueImpl implements ViewBanque {

    @Override
    public int afficherMenu() {
        // affichage du menu
        for (String option : Constante.MENU_BANQUE) {
            // afficher sur la console les options du menu.
            AffichageConsole.afficherMessageAvecSautLigne(option);
        }

        // Lire le choix saisi par l'utilisateur.

        return LectureConsole.lectureChoixInt(0, Constante.PLUS_GRANDE_OPTION_MENU);
    }

    @Override
    public Client saisirClient() throws DateNaissanceClientErroneeException {
        //Etape1: faire saisir un nom, un prenom, une date de naissance pour le nouveau client à créer
        System.out.println("Nom du nouveau client ?");
        String nom = LectureConsole.lectureChaineCaracteres();

        System.out.println("Prenom du nouveau client ?");
        String prenom = LectureConsole.lectureChaineCaracteres();

        System.out.println("Date de naissance du nouveau client ?");
        LocalDate dateNaissance = LectureConsole.lectureLocalDate("dd/MM/yyyy");

        //Etape 2 : demander une instance de client à la clientFactory

        return EntitiesFactory.fabriquerClient(nom, prenom, dateNaissance);

    }

    @Override
    public void afficherMessageErreur(String message) {
        AffichageConsole.afficherErreur(message);

    }


    @Override
    public Client selectionnerClient(List<Client> clients) {
//		System.out.println("Selectionner un client parmi la liste:");
//		for (int i = 0; i < clients.size(); i++) {
//			System.out.print(String.format("%d - %s%n",i+1,clients.get(i).getNom()));
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

    @Override
    public Compte selectionnerCompte(Client client) {
        System.out.println("Selectionner un compte parmi la liste:");
        for (int i = 0; i < client.getComptes().size(); i++) {
            System.out.print(String.format("%d - %s%n", i + 1, client.getComptes().get(i)));
        }
        int select = LectureConsole.lectureChoixInt(1, client.getComptes().size());
        return client.getComptes().get(select - 1);
    }

    @Override
    public Float saisirMontant(String montant) {
        System.out.println("Saisir le montant:");
        return ((Double) LectureConsole.lectureDouble()).floatValue();
    }

    @Override
    public Compte saisirCompte(Client client) {
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

    @Override
    public void afficherClients(List<Client> clients) {
        for (Client client : clients) {
            AffichageConsole.afficherMessageAvecSautLigne(client.toString());
        }
    }

    @Override
    public TypeCompte selectionnerTypeCompte() {
        //TODO
        return null;
    }

    @Override
    public void afficherUnClient(Client client) {
        // TODO Auto-generated method stub

    }

}
