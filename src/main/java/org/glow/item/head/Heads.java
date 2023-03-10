package org.glow.item.head;

import java.util.ArrayList;
import java.util.List;

public class Heads {

    private static final Heads heads = new Heads();
    private final List<Head> headList = new ArrayList<>();

    private Heads() {
        headList.add(new IronHeadArmor());
        headList.add(new WhiteIronHeadArmor());
    }

    public static Heads getHeads() {
        return heads;
    }

    public List<Head> getHeadList() {
        return headList;
    }
}
