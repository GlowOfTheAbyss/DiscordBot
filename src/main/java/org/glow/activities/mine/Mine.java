package org.glow.activities.mine;

import discord4j.core.object.entity.Message;
import org.glow.person.Player;

public abstract class Mine {

    private Message message;

    private Player player;

    public Mine(Message message, Player player) {
        this.message = message;
        this.player = player;
    }

    public abstract void mine();

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
