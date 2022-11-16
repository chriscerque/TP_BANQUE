package net.ent.etrs.banque.view.console;

public final class ViewFactory {

    private ViewFactory() {
    }

    public static ViewBanque viewConsoleFactory() {
        return new ViewBanqueImpl();
    }

    public static ViewBanque viewGraphiqueFactory() {
        return new ViewBanqueGraphiqueImpl();
    }
}
