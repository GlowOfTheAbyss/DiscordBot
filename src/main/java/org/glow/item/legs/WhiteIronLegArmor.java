package org.glow.item.legs;

import org.glow.item.Armor;
import org.glow.item.Items;

public class WhiteIronLegArmor implements Items, Armor, Legs {

    @Override
    public int getArmor() {
        return 10;
    }

    @Override
    public String getName() {
        return "Броня для ног из белого железа";
    }

    @Override
    public int getPrice() {
        return 4000;
    }

}
