package org.glow.item.lefthand;

import org.glow.item.Items;
import org.glow.item.Shield;

public class WhiteIronShield implements Items, Shield, LeftHand {

    @Override
    public String getName() {
        return "Щит из белого железа";
    }

    @Override
    public int getPrice() {
        return 3200;
    }

    @Override
    public int getArmor() {
        return 8;
    }
}
