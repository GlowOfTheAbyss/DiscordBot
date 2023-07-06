package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import org.glow.commands.Command;
import org.glow.map.regions.mondstadt.subRegions.FavoniusCathedral;
import org.glow.map.regions.mondstadt.subRegions.SchulzsBlacksmith;
import org.glow.map.regions.mondstadt.subRegions.actions.BuyInFavoniusCathedral;
import org.glow.map.regions.mondstadt.subRegions.actions.BuyInSchulzsBlacksmith;
import org.glow.person.PersonManager;
import org.glow.person.Player;

public class BuyCommand extends Command {

    private static BuyCommand buyCommand;

    private BuyCommand() {
        setName("buy");
        setInfo("""
                комманда для покупки предметов в специальных местах
                !buy - показать предметы доступные для покупки
                !buy [название предмета] - купить предмет
                """);
    }

    @Override
    public void start(Message message) {

        Player player = userToPlayer(message);
        if (player == null) {
            return;
        }

        if (playerInBattle(player, message)) {
            return;
        }

        if (PersonManager.getInstance().getPlayerRegion(player) instanceof SchulzsBlacksmith) {
            new BuyInSchulzsBlacksmith(message, player).startAction();
        } else if (PersonManager.getInstance().getPlayerRegion(player) instanceof FavoniusCathedral) {
            new BuyInFavoniusCathedral(message, player).startAction();
        }

    }

    public static BuyCommand getBuyCommand() {
        if (buyCommand == null) {
            buyCommand = new BuyCommand();
        }
        return buyCommand;
    }
}
