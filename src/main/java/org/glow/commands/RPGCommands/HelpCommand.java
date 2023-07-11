package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import org.glow.commands.CommandReader;
import org.glow.Main;
import org.glow.commands.Command;
import org.glow.message.MessageSender;

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

        String title = "Список существующих команд";

        StringBuilder stringBuilder = new StringBuilder();

        for (Command command : CommandReader.getCommandReader().getCommandList()) {
            if (command instanceof HelpCommand) {
                continue;
            }

            stringBuilder.append(Main.systems.commandPrefix)
                    .append(command.getName())
                    .append(" : ")
                    .append(command.getInfo())
                    .append("\n\n");
        }

        MessageSender.getInstance().sendMessageInChannel(message, title, stringBuilder.toString());

    }

    public static HelpCommand getHelpCommand() {
        if (helpCommand == null) {
            helpCommand = new HelpCommand();
        }
        return helpCommand;
    }
}
