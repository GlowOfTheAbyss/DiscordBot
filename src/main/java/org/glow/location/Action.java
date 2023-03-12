package org.glow.location;

import discord4j.core.object.entity.Message;
import org.glow.person.Player;

public abstract class Action implements LaunchedAction {

    private String name;

    @Override
    public void startAction(Message message, Player player) {}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
