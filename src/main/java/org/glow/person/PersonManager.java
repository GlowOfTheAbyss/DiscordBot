package org.glow.person;

import discord4j.common.util.Snowflake;
import org.glow.Main;
import org.glow.map.location.Location;
import org.glow.map.location.LocationManager;
import org.glow.map.regions.Region;
import org.glow.person.npc.NPC;

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
            return Main.getSystems().getGateway().getUserById(getPlayerSnowflake((Player) person)).block().getUsername();
        } else if (person instanceof NPC) {
            return ((NPC) person).getName();
        }
        throw new IllegalArgumentException("Имя игрока не найдено");
    }

    public Region getPlayerRegion(Player player) {

        for (Region location : LocationManager.getInstance().getRegions()) {

            if (player.getLocationName().equals(location.getName())) {
                return location;
            }

        }

        throw new IllegalArgumentException("Location not found");
    }

    public void setPlayerLocation(Player player, Location location) {
        player.setLocationName(location.getName());
    }

    public int getPlayerMaxHealth(Player player) {
        return (10 + player.getEndurance()) * 10;
    }

    public int getPlayerMaxMana(Player player) {
        return (10 + ((int) (0.5 * player.getIntelligence()))) * 10;
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
