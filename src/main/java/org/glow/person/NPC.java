package org.glow.person;

public abstract class NPC extends Person {

    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int getCoins() {
        return getLevel() * 10;
    }
}
