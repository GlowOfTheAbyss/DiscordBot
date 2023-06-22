package org.glow.item.legs;

import org.glow.item.Armor;
import org.glow.storage.Inventory;

public class Legs extends Armor {

    @Override
    public void equipItem(Inventory inventory) {

        Legs legs = inventory.getLegs();

        inventory.setLegs(this);

        if (legs instanceof NoneLegs) {

            inventory.getBag().add(legs);

        }

    }
}
