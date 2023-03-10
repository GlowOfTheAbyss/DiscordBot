package org.glow.item.body;

import org.glow.item.Armor;
import org.glow.item.Items;

public class BodyCreated implements Items, Armor, Body {

    private String name;
    private int armor;
    private int price;

    public BodyCreated() {
    }

    public BodyCreated(String name, int armor, int price) {
        this.name = name;
        this.armor = armor;
        this.price = price;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
