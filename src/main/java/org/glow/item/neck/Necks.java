package org.glow.item.neck;

import java.util.ArrayList;
import java.util.List;

public class Necks {

    private static final Necks necks = new Necks();

    private final List<Neck> neckList = new ArrayList<>();

    private Necks(){
    }

    public static Necks getNecks() {
        return necks;
    }

    public List<Neck> getNeckList() {
        return neckList;
    }
}
