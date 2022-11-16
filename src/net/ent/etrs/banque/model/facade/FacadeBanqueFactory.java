package net.ent.etrs.banque.model.facade;

public final class FacadeBanqueFactory {

    private FacadeBanqueFactory() {
    }

    public static FacadeMetierBanque facadeMetierFactory() {
        return new FacadeMetierImpl();
    }

}
