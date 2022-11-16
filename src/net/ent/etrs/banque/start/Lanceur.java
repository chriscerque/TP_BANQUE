package net.ent.etrs.banque.start;

import net.ent.etrs.banque.model.facade.FacadeBanqueFactory;
import net.ent.etrs.banque.model.facade.FacadeMetierBanque;
import net.ent.etrs.banque.presenter.BanquePresenter;
import net.ent.etrs.banque.view.console.ViewFactory;

public final class Lanceur {

    private Lanceur() {
    }

    public static void main(String[] args) {
        FacadeMetierBanque metier = FacadeBanqueFactory.facadeMetierFactory();
        metier.init();

        BanquePresenter presenter = new BanquePresenter(ViewFactory.viewConsoleFactory(), metier);
        presenter.execute();

    }

}
