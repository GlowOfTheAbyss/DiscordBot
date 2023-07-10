package org.glow.item.lefthand;

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

    @Override
    public void unequipItem(Inventory inventory) {

        LeftHand leftHand = inventory.getLeftHand();
        inventory.setLeftHand(leftHand);

        inventory.getBag().add(leftHand);

    }
}