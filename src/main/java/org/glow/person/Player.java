package org.glow.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import discord4j.common.util.Snowflake;
import org.glow.inventory.Inventory;
import org.glow.inventory.SkillBook;
import org.glow.location.Location;
import org.glow.location.Map;
import org.glow.location.region.mondstadt.Mondstadt;

import java.util.ArrayList;
import java.util.List;

import static org.glow.Main.gateway;

public class Player extends Person {

    private static final List<Player> playerList = new ArrayList<>();

    private Location location;

    public Player() {}

    public Player(Snowflake snowflake) {

        this.stringSnowflake = snowflake.asString();

        this.location = Mondstadt.getMondstadt();

        this.health = getMaxHealth();
        this.mana = getMaxMana();
        this.energy = 5;
        this.coins = 5000;

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
        return gateway.getUserById(getSnowflake()).block().getUsername();
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
