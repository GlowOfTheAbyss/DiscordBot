package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import org.glow.commands.Command;
import org.glow.map.regions.mondstadt.subRegions.FavoniusCathedral;
import org.glow.map.regions.mondstadt.subRegions.actions.HealFavoniusCathedral;
import org.glow.person.PersonManager;
import org.glow.person.Player;

public class HealCommand extends Command {

    private static HealCommand healCommand;

    private HealCommand() {
        setName("heal");
        setInfo("""
                команда для лечения персонажа в специальных заведениях
                !heal - показывает подробную информацию о услуге
                !heal [количество здоровья что нужно востановить]
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

        if (PersonManager.getInstance().getPlayerRegion(player) instanceof FavoniusCathedral) {
            new HealFavoniusCathedral(message, player).startAction();
        }

    }

    public static HealCommand getHealCommand() {
        if (healCommand == null) {
            healCommand = new HealCommand();
        }
        return healCommand;
    }
}
