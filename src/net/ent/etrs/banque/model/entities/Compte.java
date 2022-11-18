package net.ent.etrs.banque.model.entities;

import net.ent.etrs.banque.model.entities.exceptions.*;
import net.ent.etrs.banque.model.entities.references.ConstantesModel;
import net.ent.etrs.banque.model.entities.references.TypeCompte;

import java.util.UUID;

/**
 * Classe représentant un compte client.
 */
public class Compte {
    /**
     * Identifiant du compte.
     */
    private final UUID id = UUID.randomUUID();
    private float solde;
    private float decouvertAutorise;
    private TypeCompte typeCompte;


//	// construteurs
//	public Compte(Client client) {
//		this.client = client;
//		this.typeCompte = TypeCompte.COMPTE_COURANT;
//		this.decouvertAutorise = BigDecimal.ZERO;
//		this.solde = BigDecimal.ZERO;
//	}


    public Compte(float decouvertAutorise, TypeCompte typeCompte) throws CompteException {
        try {
            this.setTypeCompte(typeCompte);
            this.setDecouvertAutorise(decouvertAutorise);
        } catch (CompteTypeCompteException e) {
            throw new CompteException(ConstantesModel.COMPTE_EXCEPTION, e);
        }


    }


    // accesseurs
    public UUID getId() {
        return this.id;
    }

    public float getSolde() {
        return this.solde;
    }

    public float getDecouvertAutorise() {
        return this.decouvertAutorise;
    }

    public void setDecouvertAutorise(Float decouvertAutorise) {
        this.decouvertAutorise = decouvertAutorise;
    }

    public TypeCompte getTypeCompte() {
        return this.typeCompte;
    }

    public void setTypeCompte(TypeCompte typeCompte) throws CompteTypeCompteException {

        if (typeCompte == null) {
            throw new CompteTypeCompteException(ConstantesModel.COMPTE_TYPE_COMPTE_NON_RENSEIGNE_EXCEPTION);
        }
        //TODO controle des types de comptes
        this.typeCompte = typeCompte;
    }

    // identité
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    public void crediter(float montant) throws ClientException {
        try {
            this.controlerMontantPositif(montant);
            this.operationSolde(montant);
        } catch (CompteMontantNegatifException | CompteDecouvertAutoriseDepasseException e) {
            throw new ClientException(e.getMessage(), e);
        }

    }

    public void debiter(float montant) throws ClientException {
        try {
            this.controlerMontantPositif(montant);
            this.operationSolde(-1 * montant);
        } catch (CompteMontantNegatifException | CompteDecouvertAutoriseDepasseException e) {
            throw new ClientException(e.getMessage(), e);
        }

    }

    private void controlerMontantPositif(float montant) throws CompteMontantNegatifException {
        if (montant < 0) {
            throw new CompteMontantNegatifException(this.id);
        }
    }

    private void operationSolde(float montant) throws CompteDecouvertAutoriseDepasseException {
        Float valeur = this.solde += montant;
        if (valeur.compareTo(-1 * decouvertAutorise) < 0) {
            throw new CompteDecouvertAutoriseDepasseException(id);
        }

        this.solde = valeur;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Compte other = (Compte) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return String.format("\tCompte [id=%s, typeCompte=%-15s, solde=%10.2f €, decouvertAutorise=%10.2f €]", id.toString(), typeCompte.name(), solde,
                decouvertAutorise);
    }


}
