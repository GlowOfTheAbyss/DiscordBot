package org.glow.activities.chests;

import discord4j.core.object.entity.Message;
import org.glow.item.Item;
import org.glow.person.PersonManager;
import org.glow.person.Player;

public abstract class Chest {

    private Message message;
    private Player player;

    public Chest(Message message, Player player) {
        this.message = message;
        this.player = player;
    }

    public abstract void openChest();

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

}
