package org.glow.item.lefthand;

import java.util.ArrayList;
import java.util.List;

public class LeftHands {

    private static final LeftHands hands = new LeftHands();

    private final List<LeftHand> handList = new ArrayList<>();

    private LeftHands() {
        handList.add(new IronShield());
        handList.add(new WhiteIronShield());
    }

    public static LeftHands getLeftHands() {
        return hands;
    }

    public List<LeftHand> getHandList() {
        return handList;
    }
}
