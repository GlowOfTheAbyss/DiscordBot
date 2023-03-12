package org.glow.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import discord4j.common.util.Snowflake;
import org.glow.Main;
import org.glow.inventory.Inventory;
import org.glow.inventory.SkillBook;
import org.glow.location.Location;
import org.glow.location.region.mondstadt.Mondstadt;

import java.util.ArrayList;
import java.util.List;

public class Player extends Person {

    private static final List<Player> playerList = new ArrayList<>();

    private Location location;

    public Player() {}

    public Player(Snowflake snowflake) {

        this.stringSnowflake = snowflake.asString();

        this.location = Mondstadt.getMondstadt();

        this.health = getMaxHealth();
        this.mana = getMaxMana();
        this.energy = 50;
        this.coins = 90000;

        this.strength = 0;
        this.endurance = 0;
        this.agility = 0;
        this.intelligence = 0;
        this.perception = 0;
        this.luck = 0;

        this.inventory = new Inventory();
        this.skillBook = new SkillBook();

        playerList.add(this);
    }

    @Override
    public String getName() {
        return Main.systems.gateway.getUserById(getSnowflake()).block().getUsername();
    }

    @JsonIgnore
    public int getMaxHealth() {
        return 100 + (endurance * 10);
    }

    @JsonIgnore
    public int getMaxMana() {
        return 100 + ((int) (0.5 * intelligence)) * 10;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public static List<Player> getPlayerList() {
        return playerList;
    }

    public static void addPlayerList(Player player) {
        playerList.add(player);
    }

}
