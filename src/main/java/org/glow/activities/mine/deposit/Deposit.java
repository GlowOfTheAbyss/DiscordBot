package org.glow.activities.mine.deposit;

import discord4j.core.object.entity.Message;
import org.glow.message.MessageSender;
import org.glow.message.Parameters;
import org.glow.message.TextManager;
import org.glow.person.PersonManager;
import org.glow.person.Player;

public abstract class Deposit {

    private Message message;

    private Player player;

    public Deposit(Message message, Player player) {
        this.message = message;
        this.player = player;
    }

    public abstract void find();

    protected void createMessage(String title, int coins) {

        String description = """
                %s получает %s %s
                
                %s
                """;

        description = String.format(description,
                PersonManager.getInstance().getPersonName(getPlayer()),
                coins,
                Parameters.COINS.getName().replaceFirst("а", "ы"),
                TextManager.getInstance().getPlayerParameters(getPlayer()));

        MessageSender.getInstance().sendMessageInChannel(getMessage(), title, description);

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

}
