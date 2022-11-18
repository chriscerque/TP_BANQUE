package net.ent.etrs.banque.model.entities.references;

/**
 * Classe regroupant toutes les constantes de
 * l'application banque.
 *
 * @author christophe.cerqueira
 */
public final class ConstantesModel {


    // messages d'erreur sur les clients
    public static final String MSG_CLIENT_EXCEPTION = "Probleme sur le traitement d'un client";
    public static final String MSG_CREATION_CLIENT = "Problème de création du client";
    public static final String MSG_DATE_NAISSANCE_CLIENT_ERRONEE = "La date de naissance du client %s %s est erronée.";
    public static final String MSG_AJOUTER_COMPTE_CLIENT_EN_DOUBLE = "Le compte n°%s (%s) existe déjà pour le client : %s %s";
    public static final String COMPTE_EXCEPTION = "Problème interne lié à une opération sur le Compte : %s.";
    public static final String COMPTE_DECOUVERT_AUTORISE_DEPASSE_EXCEPTION = "Le solde du compte (%s) est inférieur au découvert autorisé";
    public static final String COMPTE_MONTANT_NEGATIF_EXCEPTION = "Le montant de l'opération est négatif pour le compte : %s.";
    public static final String CLIENT_CREATION_EXCEPTION = "Le client %s %s n'a pas pu être créé";
    public static final String CLIENT_AJOUTER_TYPE_COMPTE_EXCEPTION = "Le client %s %s possède déjà un compte de type %s";
    public static final String PERSONNE_NOM_NON_RENSEIGNE_EXCEPTION = "Le nom du client doit être renseigné.";
    public static final String PERSONNE_PRENOM_NON_RENSEIGNE_EXCEPTION = "Le prénom du client doit être renseigné.";
    public static final String COMPTE_TYPE_COMPTE_NON_RENSEIGNE_EXCEPTION = "Le type de compte doit être renseigné.";

    /**
     * Consteur privé pour interdir l'instanciation.
     */
    private ConstantesModel() {
    }


}
