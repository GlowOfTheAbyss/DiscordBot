package org.glow.item.body;

import java.util.ArrayList;
import java.util.List;

public class Bodys {

    private static final Bodys bodys = new Bodys();

    private final List<Body> bodyList = new ArrayList<>();

    private Bodys() {
        bodyList.add(new IronBodyArmor());
        bodyList.add(new WhiteIronBodyArmor());
    }

    public static Bodys getBodys() {
        return bodys;
    }

    public List<Body> getBodyList() {
        return bodyList;
    }
}
