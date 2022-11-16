package net.ent.etrs.banque.model.entities;

import net.ent.etrs.banque.model.entities.exceptions.ClientException;
import net.ent.etrs.banque.model.entities.exceptions.CompteDecouvertAutoriseDepasseException;
import net.ent.etrs.banque.model.entities.exceptions.CompteMontantNegatifException;
import net.ent.etrs.banque.model.entities.references.TypeCompte;

import java.util.UUID;

/**
 * Classe représentant un compte client.
 *
 * @author Christophe LOUËR
 */
public class Compte {
    /**
     * Identifiant du compte.
     */
    private final UUID id = UUID.randomUUID();
    private Float solde;
    private Float decouvertAutorise;
    private TypeCompte typeCompte;


//	// construteurs
//	public Compte(Client client) {
//		this.client = client;
//		this.typeCompte = TypeCompte.COMPTE_COURANT;
//		this.decouvertAutorise = BigDecimal.ZERO;
//		this.solde = BigDecimal.ZERO;
//	}


    public Compte(Float decouvertAutorise, TypeCompte typeCompte) {
        this.typeCompte = typeCompte;
        this.decouvertAutorise = decouvertAutorise;
    }


    // accesseurs
    public UUID getId() {
        return this.id;
    }

    public Float getSolde() {
        return this.solde;
    }

    public Float getDecouvertAutorise() {
        return this.decouvertAutorise;
    }

    public void setDecouvertAutorise(Float decouvertAutorise) {
        this.decouvertAutorise = decouvertAutorise;
    }

    public TypeCompte getTypeCompte() {
        return this.typeCompte;
    }

    // identité
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    public void crediter(Float montant) throws ClientException {
        try {
            this.controlerMontantPositif(montant);
            this.operationSolde(montant);
        } catch (CompteMontantNegatifException | CompteDecouvertAutoriseDepasseException e) {
            throw new ClientException(e.getMessage(), e);
        }

    }

    public void debiter(Float montant) throws ClientException {
        try {
            this.controlerMontantPositif(montant);
            this.operationSolde(-1 * montant);
        } catch (CompteMontantNegatifException | CompteDecouvertAutoriseDepasseException e) {
            throw new ClientException(e.getMessage(), e);
        }

    }

    private void controlerMontantPositif(Float montant) throws CompteMontantNegatifException {
        if (montant < 0) {
            throw new CompteMontantNegatifException(this.id);
        }
    }

    private void operationSolde(Float montant) throws CompteDecouvertAutoriseDepasseException {
        Float valeur = this.solde += montant;
        if (valeur.compareTo(decouvertAutorise) < 0) {
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
