package org.glow.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import discord4j.common.util.Snowflake;
import org.glow.storage.Inventory;
import org.glow.storage.SkillBook;

public abstract class Person {

    private String stringSnowflake;
    private String name;

    private int health;
    private int mana;
    private int energy;
    private int coins;

    private int strength;
    private int endurance;
    private int agility;
    private int intelligence;
    private int perception;
    private int luck;

    private Inventory inventory;

    private SkillBook skillBook;

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

    public void setSkillBook(SkillBook skillBook) {
        this.skillBook = skillBook;
    }

}


