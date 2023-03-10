package org.glow;

import discord4j.core.object.entity.Message;
import org.glow.commands.Command;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommandReader {

    private static final CommandReader commandReader = new CommandReader();

    private CommandReader() {}

    public void readCommand(Message message) {
        String thisCommandName = getCommand(message);
        ExecutorService executorService = Executors.newFixedThreadPool(4, r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });

        for (Command command : Command.getCommand().getCommandList()) {
            if (command.getName().equals(thisCommandName)) {
                executorService.submit(() -> command.start(message));
            }
        }

    }

    private String getCommand(Message message) {
        return message.getContent().split(" ")[0].replaceFirst("!", "");
    }

    public static CommandReader getCommandReader() {
        return commandReader;
    }

}