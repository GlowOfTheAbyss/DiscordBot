package org.glow.item.body;

import org.glow.item.Armor;
import org.glow.item.Items;

public class IronBodyArmor implements Items, Armor, Body {

    @Override
    public int getArmor() {
        return 6;
    }

    @Override
    public String getName() {
        return "Нагрудная броня из железа";
    }

    @Override
    public int getPrice() {
        return 2400;
    }
}
