package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import org.glow.commands.Command;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.cityMondstadt.FavoniusCathedral;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.cityMondstadt.SchulzsBlacksmith;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.cityMondstadt.actions.BuyInFavoniusCathedral;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.cityMondstadt.actions.BuyInSchulzsBlacksmith;
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

        if (player.getLocation() instanceof SchulzsBlacksmith) {
            BuyInSchulzsBlacksmith.getBuyInSchulzsBlacksmith().startAction(message, player);
        } else if (player.getLocation() instanceof FavoniusCathedral) {
            BuyInFavoniusCathedral.getBuyFavoniusCathedral().startAction(message, player);
        }

    }

    public static BuyCommand getBuyCommand() {
        return buyCommand;
    }
}
