package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.commands.Command;
import org.glow.fileManager.Save;
import org.glow.location.region.mondstadt.subareas.WhisperingWoods;
import org.glow.location.region.mondstadt.subareas.actions.WhisperingWoodsAdventures;
import org.glow.person.PersonManager;
import org.glow.person.Player;

public class GoCommand extends Command {

    private static GoCommand goCommand;

    private GoCommand() {
        setName("go");
        setInfo("""
                отправиться в приключения в специальных локациях
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

        if (player.getEnergy() <= 0) {
            notEnoughEnergyMessage(message);
            return;
        }

        if (PersonManager.getInstance().getPlayerLocation(player) instanceof WhisperingWoods) {

            player.setEnergy(player.getEnergy() - 1);
            Save.getSave().saveFile(player);

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
