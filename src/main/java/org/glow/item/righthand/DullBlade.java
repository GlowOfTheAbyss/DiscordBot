package org.glow.item.righthand;

import org.glow.item.Items;
import org.glow.item.Weapon;

public class DullBlade implements Items, Weapon, RightHand {

    @Override
    public String getName() {
        return "Тупой меч";
    }

    @Override
    public int getPrice() {
        return 4500;
    }

    @Override
    public int getAttack() {
        return 15;
    }

}
