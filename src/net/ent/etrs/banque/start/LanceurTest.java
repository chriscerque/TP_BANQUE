package net.ent.etrs.banque.start;

import net.ent.etrs.banque.model.entities.Client;
import net.ent.etrs.banque.model.facade.FacadeMetierBanque;
import net.ent.etrs.banque.model.facade.FacadeMetierFactory;

public class LanceurTest {

    public static void main(String[] args) {
        FacadeMetierBanque facade = FacadeMetierFactory.facadeMetierFactory();
//        facade.init();

        for (Client client : facade.listerClient()) {
            System.out.println(client);
        }
    }

}
