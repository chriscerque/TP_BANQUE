package net.ent.etrs.banque.model.entities.references;

/**
 * Enumération regroupant tous les types de compte.
 * Compte courant, livret A, livret dév. durable.
 *
 * @author christophe.cerqueira
 */
public enum TypeCompte {
    COMPTE_COURANT("Compte courant"),
    LIVRET_A("Livret A"),
    LIVRET_DVD("Livret développement durable");

    private final String libelle;

    private TypeCompte(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return this.libelle;
    }


}
