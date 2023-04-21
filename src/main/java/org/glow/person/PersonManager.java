package org.glow.person;

import discord4j.common.util.Snowflake;
import org.glow.Main;
import org.glow.location.Location;
import org.glow.location.Map;

import java.util.ArrayList;
import java.util.List;

public class PersonManager {

    private static PersonManager personManager;
    private static final List<Player> playerList = new ArrayList<>();

    private PersonManager() {}

    public int getPersonLevel(Person person) {
        return person.getStrength() + person.getEndurance() + person.getAgility()
                + person.getIntelligence() + person.getPerception() + person.getLuck();
    }

    public int getPersonCombatLevel(Person person) {
        return person.getStrength() + person.getEndurance() + person.getAgility();
    }

    public Snowflake getPlayerSnowflake(Player player) {
        return Snowflake.of(player.getSnowflake());
    }

    public void setPlayerSnowflake(Player player, Snowflake snowflake) {
        player.setSnowflake(snowflake.asString());
    }

    public String getPersonName(Person person) {
        if (person instanceof Player) {
            return Main.systems.gateway.getUserById(getPlayerSnowflake((Player) person)).block().getUsername();
        } else if (person instanceof NPC) {
            return ((NPC) person).getName();
        } else {
            throw new IllegalArgumentException("Person name not found");
        }
    }

    public Location getPlayerLocation(Player player) {
        for (Location location : Map.getMap().getLocations()) {
            if (location.getName().equalsIgnoreCase(player.getLocationName())) {
                return location;
            }
        }
        throw new IllegalArgumentException("Location not found");
    }

    public void setPlayerLocation(Player player, Location location) {
        player.setLocationName(location.getName());
    }

    public int getPlayerMaxHealth(Player player) {
        return 100 + (player.getEndurance() * 10);
    }

    public int getplayerMaxMana(Player player) {
        return 100 + ((int) (0.5 * player.getIntelligence()) * 10);
    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public List<Player> getPlayers() {
        return playerList;
    }

    public static PersonManager getInstance() {
        if (personManager == null) {
            personManager = new PersonManager();
        }
        return personManager;
    }

}
