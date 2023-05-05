package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.commands.Command;
import org.glow.location.region.mondstadt.subareas.FavoniusCathedral;
import org.glow.location.region.mondstadt.subareas.SchulzsBlacksmith;
import org.glow.location.region.mondstadt.subareas.actions.BuyInFavoniusCathedral;
import org.glow.location.region.mondstadt.subareas.actions.BuyInSchulzsBlacksmith;
import org.glow.person.PersonManager;
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

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        if (playerInBattle(player)) {
            builder.title("Находясь в битве нельзя это использовать");
            message.getChannel().block().createMessage(builder.build()).block();
            message.delete().block();
            return;
        }

        if (PersonManager.getInstance().getPlayerLocation(player) instanceof SchulzsBlacksmith) {
            BuyInSchulzsBlacksmith.getBuyInSchulzsBlacksmith().startAction(message, player);
        } else if (PersonManager.getInstance().getPlayerLocation(player) instanceof FavoniusCathedral) {
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
