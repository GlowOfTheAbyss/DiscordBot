package org.glow.item.finger;

import org.glow.item.Item;
import org.glow.storage.Inventory;

public class Finger extends Item {

    @Override
    public void equipItem(Inventory inventory) {

        Finger finger = inventory.getFinger();

        inventory.setFinger(this);

        if (!(finger instanceof NoneFinger)) {

            inventory.getBag().add(finger);

        }

    }

    @Override
    public void unequipItem(Inventory inventory) {

        Finger finger = inventory.getFinger();
        inventory.setFinger(new NoneFinger());

        inventory.getBag().add(finger);

    }
}
