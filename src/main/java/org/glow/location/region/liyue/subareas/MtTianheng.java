package org.glow.location.region.liyue.subareas;

import org.glow.location.Region;
import org.glow.location.region.liyue.Liyue;
import org.glow.location.region.liyue.subareas.actions.DigOnMtTianheng;

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
