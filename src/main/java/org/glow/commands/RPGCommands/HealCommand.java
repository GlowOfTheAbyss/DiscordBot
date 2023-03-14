package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import org.glow.commands.Command;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.mondstadt.FavoniusCathedral;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.mondstadt.actions.HealFavoniusCathedral;
import org.glow.person.Player;

public class HealCommand extends Command {

    private static final HealCommand healCommand = new HealCommand();

    private HealCommand() {
        setName("heal");
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
        return healCommand;
    }
}
