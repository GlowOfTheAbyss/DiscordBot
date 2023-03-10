package org.glow.location;

import discord4j.core.object.entity.Message;
import org.glow.person.Player;

public class Action implements LaunchedAction {

    private static final Action action = new Action();
    protected String name;

    @Override
    public void startAction(Message message, Player player) {}

    public String getName() {
        return name;
    }

    public static Action getAction() {
        return action;
    }

}
