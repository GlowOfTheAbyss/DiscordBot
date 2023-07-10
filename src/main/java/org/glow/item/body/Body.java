package org.glow.item.body;

import org.glow.item.Armor;
import org.glow.storage.Inventory;

public class Body extends Armor {

    @Override
    public void equipItem(Inventory inventory) {

        Body body = inventory.getBody();

        inventory.setBody(this);

        if (!(body instanceof NoneBody)) {

            inventory.getBag().add(body);

        }

    }

    @Override
    public void unequipItem(Inventory inventory) {

        Body body = inventory.getBody();
        inventory.setBody(new NoneBody());

        inventory.getBag().add(body);

    }

}
