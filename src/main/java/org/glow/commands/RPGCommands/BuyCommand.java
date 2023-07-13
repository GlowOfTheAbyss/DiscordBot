package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import org.glow.Main;
import org.glow.commands.Command;
import org.glow.map.regions.mondstadt.subRegions.FavoniusCathedral;
import org.glow.map.regions.mondstadt.subRegions.SchulzsBlacksmith;
import org.glow.map.regions.mondstadt.subRegions.actions.BuyInFavoniusCathedral;
import org.glow.map.regions.mondstadt.subRegions.actions.BuyInSchulzsBlacksmith;
import org.glow.message.MessageSender;
import org.glow.person.PersonManager;
import org.glow.person.Player;

public class BuyCommand extends Command {

    private static BuyCommand buyCommand;

    private BuyCommand() {
        setName("buy");
        String info = """
                команда для покупки предметов в специальных местах
                %s%s - показать предметы доступные для покупки
                %s%s [название предмета] - купить предмет
                """;
        setInfo(String.format(info,
                Main.getSystems().getCommandPrefix(), getName(),
                Main.getSystems().getCommandPrefix(), getName()));
    }

    @Override
    public void start(Message message) {

        try {

            Player player = userToPlayer(message);

            if (playerInBattle(player)) {
                MessageSender.getInstance().sendMessageInChannel(message, "Находясь в битве нельзя это использовать");
                return;
            }

            if (PersonManager.getInstance().getPlayerRegion(player) instanceof SchulzsBlacksmith) {
                new BuyInSchulzsBlacksmith(message, player).startAction();
            } else if (PersonManager.getInstance().getPlayerRegion(player) instanceof FavoniusCathedral) {
                new BuyInFavoniusCathedral(message, player).startAction();
            }

        } catch (IllegalArgumentException exception) {
            MessageSender.getInstance().sendMessageInChannel(message, exception.getMessage());
        }

    }

    public static BuyCommand getBuyCommand() {
        if (buyCommand == null) {
            buyCommand = new BuyCommand();
        }
        return buyCommand;
    }
}
