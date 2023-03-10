package org.glow.item.lefthand;

import org.glow.item.Items;
import org.glow.item.Shield;

public class IronShield implements Items, Shield, LeftHand {

    @Override
    public String getName() {
        return "Щит из железа";
    }

    @Override
    public int getPrice() {
        return 1600;
    }

    @Override
    public int getArmor() {
        return 4;
    }
}
