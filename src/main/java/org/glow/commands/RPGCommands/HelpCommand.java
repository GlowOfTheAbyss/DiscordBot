package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.CommandReader;
import org.glow.Main;
import org.glow.commands.Command;
import org.glow.person.Player;

public class HelpCommand extends Command {

    private static HelpCommand helpCommand;

    private HelpCommand() {
        setName("help");
        setInfo("""
                команда для помощи
                """);
    }


    @Override
    public void start(Message message) {

        Player player = userToPlayer(message);
        if (player == null) {
            return;
        }

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        builder.title("Список существующих команд");

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(Main.systems.commandPrefix)
                .append(" префикс для всех комманд")
                .append("\n\n");

        for (Command command : CommandReader.getCommandReader().getCommandList()) {

            if (command instanceof HelpCommand) {
                continue;
            }

            stringBuilder.append(command.getName())
                    .append(" : ")
                    .append(command.getInfo())
                    .append("\n\n");

        }

        builder.description(stringBuilder.toString());
        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    public static HelpCommand getHelpCommand() {
        if (helpCommand == null) {
            helpCommand = new HelpCommand();
        }
        return helpCommand;
    }
}
