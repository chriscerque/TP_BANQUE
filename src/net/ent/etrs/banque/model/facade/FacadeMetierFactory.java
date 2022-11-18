package net.ent.etrs.banque.model.facade;

public final class FacadeMetierFactory {

    private FacadeMetierFactory() {
    }

    public static FacadeMetierBanque facadeMetierFactory() {
        return new FacadeMetierImpl();
    }

}
