package org.glow.location.region.liyue;

import org.glow.location.Region;
import org.glow.location.region.liyue.subareas.LiyueHarbor;
import org.glow.location.region.liyue.subareas.MtTianheng;
import org.glow.location.region.liyue.subareas.TheChasm;

import java.util.Set;

public class Liyue extends Region {
    private static Liyue liyue;

    private Liyue() {
        setName("Ли Юэ");
        setImage("https://cdn.discordapp.com/attachments/1066672288897978439/1086592143709184040/Liyue.webp");

        setSubareas(Set.of(LiyueHarbor.getLiyueHarbor(),
                TheChasm.getTheChasm(),
                MtTianheng.getMtTianheng()));
    }

    public static Liyue getLiyue() {
        if (liyue == null) {
            liyue = new Liyue();
        }
        return liyue;
    }

}
