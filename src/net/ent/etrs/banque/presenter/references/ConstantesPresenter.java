package net.ent.etrs.banque.presenter.references;

/**
 * Classe utilitaire regroupant les constantes et méthodes
 * utiles aux vues console.
 *
 * @author Christophe LOUËR
 */
public final class ConstantesPresenter {

    /**
     * Constante représentant le menu principal de
     * l'application.
     */
    public static final String[] MENU_BANQUE = {
            "+----------- BANQUE -----------+",
            "|                              |",
            "| 1. Liste des clients         |",
            "| 2. Créer un client           |",
            "| 3. Supprimer un client       |",
            "| 4. Ajouter un compte client  |",
            "| 5. Créditer un compte        |",
            "| 6. Débiter un compte         |",
            "| 7. Afficher un client        |",
            "| 0. Sortir                    |",
            "|                              |",
            "+------------------------------+",
            "CHOIX : "
    };
    public static final int PLUS_GRANDE_OPTION_MENU = 7;

    private ConstantesPresenter() {
    }

}
