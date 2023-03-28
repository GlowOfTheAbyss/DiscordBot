package org.glow;

import discord4j.core.object.entity.Message;
import org.glow.commands.Command;
import org.glow.commands.RPGCommands.*;
import org.glow.commands.SystrmCommands.ShotDownCommand;

import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommandReader {

    private static final CommandReader commandReader = new CommandReader();
    private static HashSet<Command> commandList;

    private CommandReader() {

        commandList = new HashSet<>();

        commandList.add(BuyCommand.getBuyCommand());
        commandList.add(CastCommand.getCastCommand());
        commandList.add(EquipCommand.getEquipCommand());
        commandList.add(GoCommand.getGoCommand());
        commandList.add(HealCommand.getHealCommand());
        commandList.add(InventoryCommand.getInventoryCommand());
        commandList.add(LevelUpCommand.getLevelUpCommand());
        commandList.add(MoveCommand.getMoveCommand());
        commandList.add(StatsCommand.getStatsCommand());
        commandList.add(TakeOffCommand.getTakeOffCommand());
        commandList.add(UserToPlayerCommand.getUserToPlayerCommand());

        commandList.add(ShotDownCommand.getShotDownCommand());

    }

    public void readCommand(Message message) {
        String thisCommandName = getCommand(message);
        ExecutorService executorService = Executors.newFixedThreadPool(4, r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });

        for (Command command : commandList) {
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