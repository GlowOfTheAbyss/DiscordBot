package org.glow.item.righthand;

import org.glow.item.Items;
import org.glow.item.Weapon;

public class SilverSword implements Items, Weapon, RightHand {

    @Override
    public String getName() {
        return "Серебряный меч";
    }

    @Override
    public int getPrice() {
        return 9000;
    }

    @Override
    public int getAttack() {
        return 30;
    }

}
