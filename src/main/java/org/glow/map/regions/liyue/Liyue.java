package org.glow.map.regions.liyue;

import org.glow.map.regions.Region;
import org.glow.map.regions.liyue.subRegions.LiyueHarbor;
import org.glow.map.regions.liyue.subRegions.MtTianheng;
import org.glow.map.regions.liyue.subRegions.TheChasm;

import java.util.Set;

public class Liyue extends Region {
    private static Liyue liyue;

    private Liyue() {
        setName("Ли Юэ");
        setImage("https://cdn.discordapp.com/attachments/1066672288897978439/1086592143709184040/Liyue.webp");

        setRegions(Set.of(LiyueHarbor.getLiyueHarbor(),
                TheChasm.getTheChasm(),
                MtTianheng.getMtTianheng()));
        setActions(Set.of());

    }

    public static Liyue getLiyue() {
        if (liyue == null) {
            liyue = new Liyue();
        }
        return liyue;
    }

}
