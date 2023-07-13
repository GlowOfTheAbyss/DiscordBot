package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import org.glow.Main;
import org.glow.commands.Command;
import org.glow.fileManager.Save;
import org.glow.message.MessageSender;
import org.glow.person.PersonManager;
import org.glow.person.Player;

public class UserToPlayerCommand extends Command {

    private static UserToPlayerCommand userToPlayerCommand;

    private UserToPlayerCommand() {
        setName("add");
        setInfo("""
                добавляет пользователя как нового игрока
                """);
    }

    @Override
    public void start(Message message) {

        for (Player player : PersonManager.getInstance().getPlayers()) {

            if (PersonManager.getInstance().getPlayerSnowflake(player).equals(message.getAuthor().get().getId())) {
                MessageSender.getInstance().sendMessageInChannel(message, "Такой игрок уже существует");
            }

        }

        Player player = new Player(message.getAuthor().get().getId());
        Save.getSave().saveFile(player);

        String title = "Игрок: " + PersonManager.getInstance().getPersonName(player) + " успешно создан";
        String description = """
                Используй команду %s%s
                """;
        description = String.format(description, Main.getSystems().getCommandPrefix(), HelpCommand.getHelpCommand().getName());

        MessageSender.getInstance().sendMessageInChannel(message, title, description);

    }

    public static UserToPlayerCommand getUserToPlayerCommand() {
        if (userToPlayerCommand == null) {
            userToPlayerCommand = new UserToPlayerCommand();
        }
        return userToPlayerCommand;
    }
}
