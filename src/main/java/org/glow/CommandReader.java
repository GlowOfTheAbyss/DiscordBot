package org.glow;

import discord4j.core.object.entity.Message;
import org.glow.commands.Command;
import org.glow.commands.RPGCommands.*;
import org.glow.commands.SystrmCommands.ShutDownCommand;

import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommandReader {

    private static final CommandReader commandReader = new CommandReader();
    private final HashSet<Command> commandList;

    private CommandReader() {

        commandList = new HashSet<>();

        commandList.add(BuyCommand.getBuyCommand());
        commandList.add(CastCommand.getCastCommand());
        commandList.add(EquipCommand.getEquipCommand());
        commandList.add(GoCommand.getGoCommand());
        commandList.add(HealCommand.getHealCommand());
        commandList.add(HelpCommand.getHelpCommand());
        commandList.add(InventoryCommand.getInventoryCommand());
        commandList.add(LevelUpCommand.getLevelUpCommand());
        commandList.add(MineCommand.getMineCommand());
        commandList.add(MoveCommand.getMoveCommand());
        commandList.add(StatsCommand.getStatsCommand());
        commandList.add(TakeOffCommand.getTakeOffCommand());
        commandList.add(UserToPlayerCommand.getUserToPlayerCommand());

        commandList.add(ShutDownCommand.getShutDownCommand());

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
        return message.getContent().split(" ")[0].replaceFirst(Main.systems.commandPrefix, "");
    }

    public HashSet<Command> getCommandList() {
        return commandList;
    }

    public static CommandReader getCommandReader() {
        return commandReader;
    }

}