package org.glow.item.lefthand;

import org.glow.item.Shield;
import org.glow.storage.Inventory;

public class LeftHand extends Shield {

    @Override
    public void equipItem(Inventory inventory) {

        LeftHand leftHand = inventory.getLeftHand();

        inventory.setLeftHand(this);

        if (!(leftHand instanceof NoneLeftHand)) {

            inventory.getBag().add(leftHand);

        }

    }

}