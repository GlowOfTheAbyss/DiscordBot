package org.glow.item.lefthand;

import org.glow.item.Items;
import org.glow.item.Shield;

public class LeftHandCreated implements Items, Shield, LeftHand {

    private String name;
    private int price;
    private int armor;

    public LeftHandCreated() {}

    public LeftHandCreated(String name, int price, int armor) {
        this.name = name;
        this.price = price;
        this.armor = armor;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public int getArmor() {
        return 0;
    }

}
