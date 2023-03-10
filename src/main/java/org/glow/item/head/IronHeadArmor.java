package org.glow.item.head;

import org.glow.item.Armor;
import org.glow.item.Items;

public class IronHeadArmor implements Items, Head, Armor {

    @Override
    public int getArmor() {
        return 2;
    }

    @Override
    public String getName() {
        return "Шлем из железа";
    }

    @Override
    public int getPrice() {
        return 800;
    }

}
