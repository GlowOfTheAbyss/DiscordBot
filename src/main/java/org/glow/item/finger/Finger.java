package org.glow.item.finger;

import org.glow.item.Item;
import org.glow.storage.Inventory;

public class Finger extends Item {

    @Override
    public void equipItem(Inventory inventory) {

        Finger leftFinger = inventory.getLeftFinger();
        Finger rightFinger = inventory.getRightFinger();

        inventory.setLeftFinger(this);

        if (!(leftFinger instanceof NoneFinger)) {

            inventory.setRightFinger(leftFinger);

            if (!(rightFinger instanceof NoneFinger)) {

                inventory.getBag().add(rightFinger);

            }

        }

    }
}
