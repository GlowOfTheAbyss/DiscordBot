package org.glow.item.legs;

import java.util.ArrayList;
import java.util.List;

public class Legss {

    private static final Legss legss = new Legss();

    private final List<Legs> legsList = new ArrayList<>();

    private Legss() {
        legsList.add(new IronLegArmor());
        legsList.add(new WhiteIronLegArmor());
    }

    public static Legss getLegss() {
        return legss;
    }

    public List<Legs> getLegsList() {
        return legsList;
    }
}
