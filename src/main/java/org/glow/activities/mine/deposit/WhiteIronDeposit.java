package org.glow.activities.mine.deposit;

import discord4j.core.object.entity.Message;
import org.glow.fileManager.Save;
import org.glow.person.PersonManager;
import org.glow.person.Player;

import java.util.Random;

public class WhiteIronDeposit extends Deposit {

    public WhiteIronDeposit(Message message, Player player) {
        super(message, player);
    }

    @Override
    public void find() {

        int coins = new Random().nextInt(2, 7)
                    + getPlayer().getStrength()
                    + (2 * getPlayer().getEndurance());
        coins *= 10;

        getPlayer().setCoins(getPlayer().getCoins() + coins);
        Save.getSave().saveFile(getPlayer());

        String title = PersonManager.getInstance().getPersonName(getPlayer()) + " находит залежь белого железа";

        createMessage(title, coins);

    }
}
