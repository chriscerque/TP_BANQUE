package net.ent.etrs.banque.view.console;

import net.ent.etrs.banque.model.entities.Client;
import net.ent.etrs.banque.model.entities.Compte;
import net.ent.etrs.banque.model.entities.EntitiesFactory;
import net.ent.etrs.banque.model.entities.exceptions.DateNaissanceClientErroneeException;
import net.ent.etrs.banque.model.entities.references.TypeCompte;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ViewBanqueGraphiqueImpl implements ViewBanque {

    public ViewBanqueGraphiqueImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public int afficherMenu() {
        StringBuilder sb = new StringBuilder();
        // affichage du menu
        for (String option : Constante.MENU_BANQUE) {
            sb.append(option);
            sb.append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
        String choix = JOptionPane.showInputDialog("Choix?");

        return Integer.parseInt(choix);

    }

    @Override
    public Client saisirClient() throws DateNaissanceClientErroneeException {
        try {
            //Etape1: faire saisir un nom, un prenom, une date de naissance pour le nouveau client à créer
            String nom = JOptionPane.showInputDialog("Nom du nouveau client ?");

            String prenom = JOptionPane.showInputDialog("Prenom du nouveau client ?");

            String date = JOptionPane.showInputDialog("Date de naissance du nouveau client ?");

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dateNaissance = LocalDate.parse(date, dtf);

            //Etape 2 : demander une instance de client à la clientFactory

            return EntitiesFactory.fabriquerClient(nom, prenom, dateNaissance);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    @Override
    public void afficherMessageErreur(String message) {
        JOptionPane.showMessageDialog(null, message);

    }

    @Override
    public void afficherClients(List<Client> clients) {
        StringBuilder sb = new StringBuilder();
        // affichage du menu
        for (Client c : clients) {
            sb.append(c.getNom());
            sb.append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());

    }

    @Override
    public Client selectionnerClient(List<Client> clients) {
        StringBuilder sb = new StringBuilder();
        sb.append("Selectionner un client parmi la liste:\n");
        for (int i = 0; i < clients.size(); i++) {
            sb.append(String.format("%d - %s%n", i + 1, clients.get(i).getNom()));
        }
        JOptionPane.showMessageDialog(null, sb.toString());
        String selectString = JOptionPane.showInputDialog(null, "Choisir le client");
        int select = Integer.parseInt(selectString);
        return clients.get(select - 1);
    }

    @Override
    public Compte selectionnerCompte(Client client) {
        StringBuilder sb = new StringBuilder();
        sb.append("Selectionner un compte parmi la liste:\n");
        for (int i = 0; i < client.getComptes().size(); i++) {
            sb.append(String.format("%d - %s%n", i + 1, client.getComptes().get(i)));
        }
        JOptionPane.showMessageDialog(null, sb.toString());
        String selectString = JOptionPane.showInputDialog(null, "Choisir le compte");
        int select = Integer.parseInt(selectString);
        return client.getComptes().get(select - 1);
    }


    @Override
    public BigDecimal saisirMontant(String montant) {
        String montantString = JOptionPane.showInputDialog(null, montant);
        return new BigDecimal(montantString);
    }

    @Override
    public Compte saisirCompte(Client client) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TypeCompte selectionnerTypeCompte() {
        TypeCompte[] tabTypeComptes = TypeCompte.values();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tabTypeComptes.length; i++) {
            sb.append(String.format("%d - %s%n", i + 1, tabTypeComptes[i]));
        }
        JOptionPane.showMessageDialog(null, sb.toString());
        String ch = JOptionPane.showInputDialog("Choisir le type de compte");
        return tabTypeComptes[Integer.parseInt(ch) - 1];
    }

    @Override
    public void afficherUnClient(Client client) {
        JOptionPane.showMessageDialog(null, client);
    }

}
