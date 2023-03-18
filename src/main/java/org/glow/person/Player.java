package org.glow.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import discord4j.common.util.Snowflake;
import org.glow.Main;
import org.glow.location.Map;
import org.glow.storage.Inventory;
import org.glow.storage.SkillBook;
import org.glow.location.Location;
import org.glow.location.region.mondstadt.Mondstadt;

import java.util.ArrayList;
import java.util.List;

public class Player extends Person {

    private static final List<Player> playerList = new ArrayList<>();

    private String locationName;

    public Player() {}

    public Player(Snowflake snowflake) {

        setStringSnowflake(snowflake.asString());

        setLocationName(Mondstadt.getMondstadt().getName());

        setStrength(0);
        setEndurance(0);
        setAgility(0);
        setIntelligence(0);
        setPerception(0);
        setLuck(0);

        setHealth(getMaxHealth());
        setMana(getMaxMana());
        setEnergy(50);
        setCoins(90000);

        setInventory(new Inventory());
        setSkillBook(new SkillBook());

        playerList.add(this);

    }

    @Override
    public String getName() {
        return Main.systems.gateway.getUserById(getSnowflake()).block().getUsername();
    }

    @JsonIgnore
    public int getMaxHealth() {
        return 100 + (getEndurance() * 10);
    }

    @JsonIgnore
    public int getMaxMana() {
        return 100 + ((int) (0.5 * getIntelligence())) * 10;
    }

    public String getLocationName() {
        return locationName;
    }

    @JsonIgnore
    public Location getLocation() {
        for (Location location : Map.getMap().getLocations()) {
            if (location.getName().equalsIgnoreCase(locationName)) {
                return location;
            }
        }
        throw new IllegalArgumentException("Location not found");
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setLocationName(Location location) {
        this.locationName = location.getName();
    }

    public static List<Player> getPlayerList() {
        return playerList;
    }

    public static void addPlayerList(Player player) {
        playerList.add(player);
    }

}
