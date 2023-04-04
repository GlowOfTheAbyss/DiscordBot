package org.glow.location.region.liyue.subareas;

import org.glow.location.Subarea;

import java.util.Set;

public class MtTianheng extends Subarea {

    private static MtTianheng mtTianheng;

    private MtTianheng() {
        setName("Гора Тяньхен");
        setImage("");

        setSubareas(Set.of());
        setActions(Set.of());
    }

    public static MtTianheng getMtTianheng() {
        if (mtTianheng == null) {
            mtTianheng = new MtTianheng();
        }
        return mtTianheng;
    }
}
