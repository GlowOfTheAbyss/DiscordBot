package org.glow.item;

public abstract class Armor extends Item implements Equipped, Unequipped {

    private int armor;

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

}
