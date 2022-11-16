package net.ent.etrs.banque.model.entities;

import net.ent.etrs.banque.model.entities.exceptions.AjouterCompteClientException;
import net.ent.etrs.banque.model.entities.exceptions.ClientException;
import net.ent.etrs.banque.model.entities.references.ConstantesModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un client de la banque.
 *
 * @author christophe.cerqueira
 */
public class Client extends AbstractPersonne {

    private final List<Compte> comptes = new ArrayList<>();

    public Client(final String nom, final String prenom) {
        super(nom, prenom);
    }

    /**
     * Getteur sur la liste des comptes du client.
     *
     * @return Une copie de la liste des comptes.
     */
    public List<Compte> getComptes() {
        //return Collections.unmodifiableList(this.comptes);
        return new ArrayList<>(this.comptes);
    }


    /**
     * Ajoute un compte au client.
     * Lève une exception si le compte à ajouter est déjà présent dans la liste.
     *
     * @param c compte à ajouter.
     * @throws ClientException
     */
    public void ajouterCompte(Compte c) throws ClientException {
        if (this.comptes.contains(c)) {
            throw new AjouterCompteClientException(c, this);
        }

        if (this.controlerTypeCompteExistant(c)) {
            throw new ClientException(String.format(ConstantesModel.CLIENT_AJOUTER_TYPE_COMPTE_EXCEPTION, this.getNom(), this.getPrenom(), c.getTypeCompte().getLibelle()));
        }

        this.comptes.add(c);

    }

    /**
     * Méthode contrôlant que le type du compte à ajouter n'est pas
     * déjà associé au client.
     *
     * @return
     */
    private boolean controlerTypeCompteExistant(Compte c) {
        boolean resultat = false;
        for (Compte compte : comptes) {
            if (compte.getTypeCompte().equals(c.getTypeCompte())) {
                resultat = true;
            }
        }
        return resultat;
    }

    @Override
    public String toString() {


        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(System.lineSeparator());
        builder.append("\tLISTE DES COMPTES :");
        for (Compte compte : comptes) {
            builder.append(System.lineSeparator());
            builder.append(compte);
        }
        builder.append(System.lineSeparator());

        return builder.toString();
    }


}
