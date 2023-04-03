package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import org.glow.commands.Command;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.cityMondstadt.FavoniusCathedral;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.cityMondstadt.SchulzsBlacksmith;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.cityMondstadt.actions.BuyInFavoniusCathedral;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.cityMondstadt.actions.BuyInSchulzsBlacksmith;
import org.glow.person.Player;

public class BuyCommand extends Command {

    private static BuyCommand buyCommand;

    private BuyCommand() {
        setName("buy");
        setInfo("""
                комманда для покупки предметов в специальных местах
                !buy - показать предметы доступные для покупки
                !buy [название предмета] - купить предмет""");
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
        if (buyCommand == null) {
            buyCommand = new BuyCommand();
        }
        return buyCommand;
    }
}
