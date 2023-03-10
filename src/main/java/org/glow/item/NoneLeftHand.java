package org.glow.item;

import org.glow.item.lefthand.LeftHand;

public class NoneLeftHand implements Items, Shield, LeftHand{

    @Override
    public String getName() {
        return "Пусто";
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public int getArmor() {
        return 0;
    }
}
