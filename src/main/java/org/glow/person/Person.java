package org.glow.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import discord4j.common.util.Snowflake;
import org.glow.inventory.Inventory;
import org.glow.inventory.SkillBook;

public abstract class Person {

    protected String stringSnowflake;
    protected String name;

    protected int health;
    protected int mana;
    protected int energy;
    protected int coins;

    protected int strength;
    protected int endurance;
    protected int agility;
    protected int intelligence;
    protected int perception;
    protected int luck;

    protected Inventory inventory;

    protected SkillBook skillBook;

    @JsonIgnore
    public Snowflake getSnowflake() {
        return Snowflake.of(stringSnowflake);
    }

    public String getStringSnowflake() {
        return stringSnowflake;
    }

    public void setStringSnowflake(String stringSnowflake) {
        this.stringSnowflake = stringSnowflake;
    }

    @JsonIgnore
    public String getName() {
        return name;
    }

    @JsonIgnore
    public int getLevel() {
        return strength + endurance + agility + intelligence + perception + luck;
    }

    @JsonIgnore
    public int getCombatLevel() {
        return strength + endurance + agility;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getPerception() {
        return perception;
    }

    public void setPerception(int perception) {
        this.perception = perception;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public SkillBook getSkillBook() {
        return skillBook;
    }


}


