package org.glow.commands;

import discord4j.core.object.entity.Message;

public interface LaunchedCommand {

    void start(Message message);

}
