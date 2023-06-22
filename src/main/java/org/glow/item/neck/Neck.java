package org.glow.item.neck;

import org.glow.item.Item;
import org.glow.storage.Inventory;

public class Neck extends Item {

    @Override
    public void equipItem(Inventory inventory) {

        Neck neck = inventory.getNeck();

        inventory.setNeck(this);

        if (!(neck instanceof NoneNeck)) {

            inventory.getBag().add(neck);

        }

    }
}
