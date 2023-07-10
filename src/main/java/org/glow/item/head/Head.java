package org.glow.item.head;

import org.glow.item.Armor;
import org.glow.storage.Inventory;

public class Head extends Armor {

    @Override
    public void equipItem(Inventory inventory) {

        Head head = inventory.getHead();

        inventory.setHead(this);

        if (!(head instanceof NoneHead)) {

            inventory.getBag().add(head);

        }

    }

    @Override
    public void unequipItem(Inventory inventory) {

        Head head = inventory.getHead();
        inventory.setHead(new NoneHead());

        inventory.getBag().add(head);

    }
}
