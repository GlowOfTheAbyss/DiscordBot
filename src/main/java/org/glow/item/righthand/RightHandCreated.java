package org.glow.item.righthand;

import org.glow.item.Items;
import org.glow.item.Weapon;

public class RightHandCreated implements Items, Weapon, RightHand {

    private String name;
    private int price;
    private int attack;

    public RightHandCreated() {}

    public RightHandCreated(String name, int price, int attack) {
        this.name = name;
        this.price = price;
        this.attack = attack;
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

    @Override
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

}
