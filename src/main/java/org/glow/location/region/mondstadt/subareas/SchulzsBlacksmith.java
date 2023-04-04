package org.glow.location.region.mondstadt.subareas;

import org.glow.location.Subarea;
import org.glow.location.region.mondstadt.subareas.actions.BuyInSchulzsBlacksmith;

import java.util.Set;

public class SchulzsBlacksmith extends Subarea {

    private static SchulzsBlacksmith schulzsBlacksmith;

    private SchulzsBlacksmith() {
        setName("Кузница Шульца");
        setImage("https://cdn.discordapp.com/attachments/882560343983915029/1065685456643162213/SchulzsBlacksmith.png");

        setSubareas(Set.of());
        setActions(Set.of(BuyInSchulzsBlacksmith.getBuyInSchulzsBlacksmith()));
    }

    public static SchulzsBlacksmith getSchulzsBlacksmith() {
        if (schulzsBlacksmith == null) {
            schulzsBlacksmith = new SchulzsBlacksmith();
        }
        return schulzsBlacksmith;
    }

}
