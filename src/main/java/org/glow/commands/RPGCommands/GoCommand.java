package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import org.glow.commands.Command;
import org.glow.map.regions.mondstadt.subRegions.WhisperingWoods;
import org.glow.map.regions.mondstadt.subRegions.actions.WhisperingWoodsAdventures;
import org.glow.message.MessageSender;
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

        try {
            Player player = userToPlayer(message);

            if (playerInBattle(player)) {
                MessageSender.getInstance().sendMessageInChannel(message, "Находясь в битве нельзя это использовать");
                return;
            }

            if (player.getEnergy() <= 0) {
                MessageSender.getInstance().sendMessageInChannel(message, "Не достаточно энергии");
                return;
            }

            if (PersonManager.getInstance().getPlayerRegion(player) instanceof WhisperingWoods) {
                new WhisperingWoodsAdventures(message, player).startAction();
            }

        } catch (IllegalArgumentException exception) {
            MessageSender.getInstance().sendMessageInChannel(message, exception.getMessage());
        }

    }

    public static GoCommand getGoCommand() {
        if (goCommand == null) {
            goCommand = new GoCommand();
        }
        return goCommand;
    }
}
