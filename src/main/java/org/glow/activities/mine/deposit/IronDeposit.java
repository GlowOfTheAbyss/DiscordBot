package org.glow.activities.mine.deposit;

import discord4j.core.object.entity.Message;
import org.glow.fileManager.Save;
import org.glow.person.PersonManager;
import org.glow.person.Player;

import java.util.Random;

public class IronDeposit extends Deposit {

    public IronDeposit(Message message, Player player) {
        super(message, player);
    }

    @Override
    public void find() {

        int coins = new Random().nextInt(1, 4)
                    + getPlayer().getStrength()
                    + getPlayer().getEndurance();
        coins *= 10;

        getPlayer().setCoins(getPlayer().getCoins() + coins);
        Save.getSave().saveFile(getPlayer());

        String title = PersonManager.getInstance().getPersonName(getPlayer()) + " находит залежь железа";

        createMessage(title, coins);

    }

}
