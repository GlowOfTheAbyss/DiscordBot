package org.glow.item;

import org.glow.item.righthand.RightHand;

public class NoneRightHand implements Items, Weapon, RightHand {

    @Override
    public int getAttack() {
        return 0;
    }

    @Override
    public String getName() {
        return "Пусто";
    }

    @Override
    public int getPrice() {
        return 0;
    }

}
