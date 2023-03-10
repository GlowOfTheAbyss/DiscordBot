package org.glow.item.legs;

import org.glow.item.Armor;
import org.glow.item.Items;

public class IronLegArmor implements Items, Armor, Legs {

    @Override
    public int getArmor() {
        return 4;
    }

    @Override
    public String getName() {
        return "Броня для ног из железа";
    }

    @Override
    public int getPrice() {
        return 1600;
    }
}
