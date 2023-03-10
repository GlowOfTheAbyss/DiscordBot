package org.glow.item.body;

import org.glow.item.Armor;
import org.glow.item.Items;

public class WhiteIronBodyArmor implements Items, Armor, Body {

    @Override
    public int getArmor() {
        return 14;
    }

    @Override
    public String getName() {
        return "Нагрудная броня из белого железа";
    }

    @Override
    public int getPrice() {
        return 5600;
    }
}
