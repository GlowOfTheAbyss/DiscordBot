package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.CommandReader;
import org.glow.commands.Command;
import org.glow.person.Player;

public class HelpCommand extends Command {

    private static HelpCommand helpCommand;

    private HelpCommand() {
        setName("help");
    }


    @Override
    public void start(Message message) {

        Player player = userToPlayer(message);
        if (player == null) {
            return;
        }

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title("Список существующих команд");
        message.getChannel().block().createMessage(builder.build()).block();
        builder.title("");

        for (Command command : CommandReader.getCommandReader().getCommandList()) {

            builder.description(command.getName() + " : " + command.getInfo());
            message.getChannel().block().createMessage(builder.build()).block();

        }

        message.delete().block();

    }

    public static HelpCommand getHelpCommand() {
        if (helpCommand == null) {
            helpCommand = new HelpCommand();
        }
        return helpCommand;
    }
}
