package org.glow.map.regions.mondstadt.subRegions;

import org.glow.map.regions.Region;
import org.glow.map.regions.mondstadt.subRegions.actions.BuyInSchulzsBlacksmith;

import java.util.Set;

public class SchulzsBlacksmith extends Region {

    private static SchulzsBlacksmith schulzsBlacksmith;

    private SchulzsBlacksmith() {
        setName("Кузница Шульца");
        setImage("https://cdn.discordapp.com/attachments/882560343983915029/1065685456643162213/SchulzsBlacksmith.png");

        setRegions(Set.of());
        setActions(Set.of(new BuyInSchulzsBlacksmith(null, null)));

    }

    public static SchulzsBlacksmith getSchulzsBlacksmith() {
        if (schulzsBlacksmith == null) {
            schulzsBlacksmith = new SchulzsBlacksmith();
        }
        return schulzsBlacksmith;
    }

}
