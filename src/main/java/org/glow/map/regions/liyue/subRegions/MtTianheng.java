package org.glow.map.regions.liyue.subRegions;

import org.glow.map.regions.Region;
import org.glow.map.regions.liyue.subRegions.actions.DigOnMtTianheng;

import java.util.Set;

public class MtTianheng extends Region {

    private static MtTianheng mtTianheng;

    private MtTianheng() {
        setName("Гора Тяньхен");
        setImage("");

        setRegions(Set.of());
        setActions(Set.of(new DigOnMtTianheng(null, null)));

    }

    public static MtTianheng getMtTianheng() {
        if (mtTianheng == null) {
            mtTianheng = new MtTianheng();
        }
        return mtTianheng;
    }
}
