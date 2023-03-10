package org.glow.magic;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import discord4j.core.object.entity.Message;
import org.glow.person.Player;

@JsonDeserialize(as = MagicCreated.class)
public interface Magic {

    String getSpellName();

    int getCoastInMana();

    int getPrice();

    void spellStart(Message message, Player player);

}
