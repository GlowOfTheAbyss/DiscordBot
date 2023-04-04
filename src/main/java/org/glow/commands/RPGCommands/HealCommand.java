package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import org.glow.commands.Command;
import org.glow.location.region.mondstadt.subareas.FavoniusCathedral;
import org.glow.location.region.mondstadt.subareas.actions.HealFavoniusCathedral;
import org.glow.person.Player;

public class HealCommand extends Command {

    private static HealCommand healCommand;

    private HealCommand() {
        setName("heal");
        setInfo("""
                команда для лечения персонажа в специальных заведениях
                !heal - показывает подробную информацию о услуге
                !heal - количество здоровья что нужно востановить""");
    }

    @Override
    public void start(Message message) {
        Player player = userToPlayer(message);
        if (player == null) {
            return;
        }

        if (player.getLocation() instanceof FavoniusCathedral) {
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
