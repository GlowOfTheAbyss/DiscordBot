package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import org.glow.commands.Command;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.starfellValley.WhisperingWoods;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.starfellValley.actions.WhisperingWoodsAdventures;
import org.glow.person.Player;

public class GoCommand extends Command {

    private static GoCommand goCommand;

    private GoCommand() {
        setName("go");
    }

    @Override
    public void start(Message message) {

        Player player = userToPlayer(message);
        if (player == null) {
            return;
        }

        if (player.getLocation() instanceof WhisperingWoods) {
            WhisperingWoodsAdventures.getWhisperingWoodsAdventures().startAction(message, player);
        }

    }

    public static GoCommand getGoCommand() {
        if (goCommand == null) {
            goCommand = new GoCommand();
        }
        return goCommand;
    }
}
