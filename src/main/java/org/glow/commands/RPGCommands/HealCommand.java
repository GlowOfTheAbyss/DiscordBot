package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.commands.Command;
import org.glow.location.region.mondstadt.subareas.FavoniusCathedral;
import org.glow.location.region.mondstadt.subareas.actions.HealFavoniusCathedral;
import org.glow.person.PersonManager;
import org.glow.person.Player;

public class HealCommand extends Command {

    private static HealCommand healCommand;

    private HealCommand() {
        setName("heal");
        setInfo("""
                команда для лечения персонажа в специальных заведениях
                !heal - показывает подробную информацию о услуге
                !heal [количество здоровья что нужно востановить]""");
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

        if (PersonManager.getInstance().getPlayerLocation(player) instanceof FavoniusCathedral) {
            HealFavoniusCathedral.getHealFavoniusCathedral().startAction(message, player);
        }

    }

    public static HealCommand getHealCommand() {
        if (healCommand == null) {
            healCommand = new HealCommand();
        }
        return healCommand;
    }
}
