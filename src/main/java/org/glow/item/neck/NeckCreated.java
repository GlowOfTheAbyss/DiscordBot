package org.glow.item.neck;

import org.glow.item.Items;

public class NeckCreated implements Items, Neck {

    private String name;
    private int price;

    public NeckCreated() {
    }

    public NeckCreated(String name, int price) {
        this.name = name;
        this.price = price;
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
