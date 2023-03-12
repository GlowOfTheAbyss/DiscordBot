package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import org.glow.commands.Command;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.mondstadt.FavoniusCathedral;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.mondstadt.SchulzsBlacksmith;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.mondstadt.actions.BuyFavoniusCathedral;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.mondstadt.actions.BuyInSchulzsBlacksmith;
import org.glow.person.Player;

public class BuyCommand extends Command {

    private static final BuyCommand buyCommand = new BuyCommand();

    private BuyCommand() {
        setName("buy");
    }

    @Override
    public void start(Message message) {

        Player player = userToPlayer(message);
        if (player == null) {
            return;
        }

        if (player.getLocation().getName().equals(SchulzsBlacksmith.getSchulzsBlacksmith().getName())) {
            BuyInSchulzsBlacksmith.getBuyInSchulzsBlacksmith().startAction(message, player);
        } else if (player.getLocation().getName().equals(FavoniusCathedral.getFavoniusCathedral().getName())) {
            BuyFavoniusCathedral.getBuyFavoniusCathedral().startAction(message, player);
        }

    }

    public static BuyCommand getBuyCommand() {
        return buyCommand;
    }
}
