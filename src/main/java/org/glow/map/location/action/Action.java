package org.glow.map.location.action;

import discord4j.core.object.entity.Message;
import org.glow.person.Player;

public abstract class Action implements LaunchedAction {

    private Message message;
    private Player player;

    private String name;
    private String description;

    public Action(Message message, Player player) {
        this.message = message;
        this.player = player;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
