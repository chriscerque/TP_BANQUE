package net.ent.etrs.banque.start;

import net.ent.etrs.banque.model.facade.FacadeMetierBanque;
import net.ent.etrs.banque.model.facade.FacadeMetierFactory;
import net.ent.etrs.banque.model.facade.exceptions.BusinessException;
import net.ent.etrs.banque.presenter.Presenter;

public final class Lanceur {

    private Lanceur() {
    }

    public static void main(String[] args) {
        FacadeMetierBanque metier = FacadeMetierFactory.facadeMetierFactory();
        try {
            metier.init();
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
        }

        Presenter presenter = new Presenter(metier);
        presenter.execute();

    }

}
