package org.glow.item.righthand;

import java.util.ArrayList;
import java.util.List;

public class RightHands {

    private static final RightHands hands = new RightHands();

    private final List<RightHand> handList = new ArrayList<>();

    private RightHands() {
        handList.add(new DullBlade());
        handList.add(new SilverSword());
    }

    public static RightHands getRightHands() {
        return hands;
    }

    public List<RightHand> getHandList() {
        return handList;
    }
}
