package org.glow.item.finger;

import org.glow.item.Items;

public class FingerCreated implements Items, Finger {

    private String name;
    private int price;

    public FingerCreated() {
    }

    public FingerCreated(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
