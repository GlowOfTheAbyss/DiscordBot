package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import org.glow.commands.Command;
import org.glow.message.MessageSender;
import org.glow.message.TextManager;
import org.glow.person.PersonManager;
import org.glow.person.Player;

public class StatsCommand extends Command {

    private static StatsCommand statsCommand;

    private StatsCommand() {
        setName("stats");
        setInfo("""
                показывает статистику персонажа
                """);
    }

    @Override
    public void start(Message message) {

        try {

            Player player = userToPlayer(message);

            String title = PersonManager.getInstance().getPersonName(player);
            String description = """
                    Локация: %s
                    
                    %s
                    
                    Уровень: %s
                    Боевой уровень: %s
                    
                    %s
                    """;
            description = String.format(description,
                    player.getLocationName(),
                    TextManager.getInstance().getPlayerParameters(player),
                    PersonManager.getInstance().getPersonLevel(player),
                    PersonManager.getInstance().getPersonCombatLevel(player),
                    TextManager.getInstance().getPlayerCharacteristic(player));

            MessageSender.getInstance().sendMessageInChannel(message, title, description);

        } catch (IllegalArgumentException exception) {
            MessageSender.getInstance().sendMessageInChannel(message, exception.getMessage());
        }

    }

    public static StatsCommand getStatsCommand() {
        if (statsCommand == null) {
            statsCommand = new StatsCommand();
        }
        return statsCommand;
    }
}
