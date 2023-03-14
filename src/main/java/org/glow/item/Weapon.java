package org.glow.item;

public abstract class Weapon extends Item {

    private int attack;

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

}
