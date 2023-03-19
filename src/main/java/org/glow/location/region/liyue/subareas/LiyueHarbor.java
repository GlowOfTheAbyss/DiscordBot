package org.glow.location.region.liyue.subareas;

import org.glow.location.Subarea;

import java.util.Set;

public class LiyueHarbor extends Subarea {

    private static LiyueHarbor liyueHarbor;

    private LiyueHarbor() {
        setName("Гавань Ли Юэ");
        setImage("https://cdn.discordapp.com/attachments/1066672288897978439/1086595990146002954/LiyueHarbor.png");

        setPointsOfInterests(Set.of());
    }

    public static LiyueHarbor getLiyueHarbor() {
        if (liyueHarbor == null) {
            liyueHarbor = new LiyueHarbor();
        }
        return liyueHarbor;
    }
}
