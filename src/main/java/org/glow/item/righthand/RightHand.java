package org.glow.item.righthand;

import org.glow.storage.Inventory;

public class RightHand extends Weapon {

    @Override
    public void equipItem(Inventory inventory) {

        RightHand rightHand = inventory.getRightHand();

        inventory.setRightHand(this);

        if (rightHand instanceof NoneRightHand) {

            inventory.getBag().add(rightHand);

        }

    }
}
