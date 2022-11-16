package net.ent.etrs.banque.model.entities;


import net.ent.etrs.banque.model.entities.exceptions.DateNaissanceClientErroneeException;
import net.ent.etrs.banque.model.entities.references.TypeCompte;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class EntitiesFactory {
    private EntitiesFactory() {
    }

    public static Client fabriquerClient(String nom, String prenom, LocalDate dateNaissance) throws DateNaissanceClientErroneeException {
        Client c = new Client(nom, prenom);
        c.setDateNaissance(dateNaissance);

        return c;
    }


    public static Compte fabriquerCompte(Client client, TypeCompte typeCompte, BigDecimal decouvertAutorise) {
        return new Compte(decouvertAutorise, typeCompte);
    }


}
