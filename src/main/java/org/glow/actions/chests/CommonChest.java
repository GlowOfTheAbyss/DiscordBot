package org.glow.actions.chests;

import discord4j.core.object.entity.Message;
import org.glow.fileManager.Save;
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

        coinsFindMessage(title, randomCoins);

    }

}
