package org.glow.item.head;

import org.glow.item.Armor;
import org.glow.item.Items;

public class WhiteIronHeadArmor implements Items, Head, Armor {

    @Override
    public int getArmor() {
        return 6;
    }

    @Override
    public String getName() {
        return "Шлем из белого железа";
    }

    @Override
    public int getPrice() {
        return 2400;
    }
}
