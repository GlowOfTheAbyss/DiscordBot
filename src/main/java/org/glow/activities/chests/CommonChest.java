package org.glow.activities.chests;

import discord4j.core.object.entity.Message;
import org.glow.fileManager.Save;
import org.glow.message.MessageSender;
import org.glow.message.Parameters;
import org.glow.person.PersonManager;
import org.glow.person.Player;

import java.util.Random;

public class CommonChest extends Chest {

    public CommonChest(Message message, Player player) {
        super(message, player);
    }

    @Override
    public void openChest() {

        String title = PersonManager.getInstance().getPersonName(getPlayer()) + " находит обычный сундук";

        int randomCoins = new Random().nextInt(5, 11) + getPlayer().getLuck();
        randomCoins *= 10;

        getPlayer().setCoins(getPlayer().getCoins() + randomCoins);
        Save.getSave().saveFile(getPlayer());

        String description = """
                %s находит %s %s
                """;
        description = String.format(description,
                PersonManager.getInstance().getPersonName(getPlayer()), randomCoins, Parameters.COINS.getName());

        MessageSender.getInstance().sendMessageInChannel(getMessage(), title, description);

    }

}
