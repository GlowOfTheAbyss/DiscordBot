package org.glow.magic;

import discord4j.core.object.entity.Message;
import org.glow.person.Player;

public interface Castable {

    void cast(Message message, Player player);

}
