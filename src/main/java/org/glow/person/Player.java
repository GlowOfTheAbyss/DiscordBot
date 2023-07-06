package org.glow.person;

import discord4j.common.util.Snowflake;
import org.glow.storage.Inventory;
import org.glow.storage.SpellBook;
import org.glow.map.regions.mondstadt.Mondstadt;

public class Player extends Person {

    private String locationName;

    public Player() {}

    public Player(Snowflake snowflake) {

        setSnowflake(snowflake.asString());

        setLocationName(Mondstadt.getMondstadt().getName());

        setStrength(0);
        setEndurance(0);
        setAgility(0);
        setIntelligence(0);
        setPerception(0);
        setLuck(0);

        setHealth(100);
        setMana(100);
        setEnergy(5);
        setCoins(10);

        setInventory(new Inventory());
        setSpellBook(new SpellBook());

        PersonManager.getInstance().addPlayer(this);

    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

}
