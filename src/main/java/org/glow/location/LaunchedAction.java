package org.glow.location;

import discord4j.core.object.entity.Message;
import org.glow.person.Player;

public interface LaunchedAction {

    void startAction(Message message, Player player);

}
