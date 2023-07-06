package org.glow.map.location.action;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.person.Player;

public abstract class Action implements LaunchedAction {

    private Message message;
    private Player player;

    private String name;
    private String desctiption;

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

    public String getDesctiption() {
        return desctiption;
    }

    public void setDesctiption(String desctiption) {
        this.desctiption = desctiption;
    }

    protected void sendMessageInChannel(String title) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title(title);
        getMessage().getChannel().block().createMessage(builder.build()).block();
        getMessage().delete().block();

    }

    protected void sendMessageInChannel(String title, String description) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title(title);
        builder.description(description);
        getMessage().getChannel().block().createMessage(builder.build()).block();
        getMessage().delete().block();

    }
    
}
