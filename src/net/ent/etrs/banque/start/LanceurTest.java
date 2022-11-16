package net.ent.etrs.banque.start;

import net.ent.etrs.banque.model.entities.Client;
import net.ent.etrs.banque.model.facade.FacadeBanqueFactory;
import net.ent.etrs.banque.model.facade.FacadeMetierBanque;

public class LanceurTest {

    public static void main(String[] args) {
        FacadeMetierBanque facade = FacadeBanqueFactory.facadeMetierFactory();
        facade.init();

        for (Client client : facade.listerClient()) {
            System.out.println(client);
        }
    }

}
