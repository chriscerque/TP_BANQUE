package net.ent.etrs.banque.model.entities;

import net.ent.etrs.banque.model.entities.exceptions.DateNaissanceClientErroneeException;
import net.ent.etrs.banque.model.entities.exceptions.PersonneNomException;
import net.ent.etrs.banque.model.entities.exceptions.PersonnePrenomException;
import net.ent.etrs.banque.model.entities.references.ConstantesModel;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Classe abstraite représentant une personne.
 */
public abstract class AbstractPersonne {
    /**
     * Identifiant de la personne.
     */
    private final UUID id = UUID.randomUUID();
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;


    public AbstractPersonne(String nom, String prenom) {
        try {
            this.setNom(nom);
            this.setPrenom(prenom);
        } catch (PersonnePrenomException | PersonneNomException e) {
            throw new RuntimeException(e);
        }

    }


    public UUID getId() {
        return this.id;
    }


    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) throws PersonneNomException {
        // Vérifie que nom soit pas null ni vide.
        if (Objects.isNull(nom) || nom.isBlank()) {
            throw new PersonneNomException(ConstantesModel.PERSONNE_NOM_NON_RENSEIGNE_EXCEPTION);
        }
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) throws PersonnePrenomException {
        // Vérifie que prénom soit pas null ni vide.
        if (Objects.isNull(nom) || nom.isBlank()) {
            throw new PersonnePrenomException(ConstantesModel.PERSONNE_PRENOM_NON_RENSEIGNE_EXCEPTION);
        }
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return this.dateNaissance;
    }

    /**
     * Setteur sur date de naissance.
     * Une exception est levée si le client n'a pas 18 ans.
     *
     * @param dateNaissance date de naissance du client.
     * @throws DateNaissanceClientErroneeException
     */
    public void setDateNaissance(LocalDate dateNaissance) throws DateNaissanceClientErroneeException {
        LocalDate dateJour = LocalDate.now();
        LocalDate date18 = LocalDate.now().minusYears(18);


        if (!dateNaissance.isBefore(date18)) {
            throw new DateNaissanceClientErroneeException(this.nom, this.prenom);
        }


        this.dateNaissance = dateNaissance;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractPersonne other = (AbstractPersonne) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "AbstractPersonne{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                '}';
    }
}
