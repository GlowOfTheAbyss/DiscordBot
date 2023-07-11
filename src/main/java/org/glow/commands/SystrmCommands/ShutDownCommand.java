package org.glow.commands.SystrmCommands;

import discord4j.core.object.entity.Message;
import org.glow.commands.Command;
import org.glow.message.MessageSender;
import org.glow.person.Player;

import java.util.concurrent.TimeUnit;

public class ShutDownCommand extends Command {

    private static final ShutDownCommand shutDownCommand = new ShutDownCommand();

    private ShutDownCommand() {
        setName("shut_down");
        setInfo("""
                всем иногда нужен отдых
                """);
    }

    @Override
    public void start(Message message) {

        try {

            Player player = userToPlayer(message);

            if (isNotAdmin(player)) {
                MessageSender.getInstance().sendMessageInChannel(message, "У вас нет прав для этой команды");
            }

            int allMinutes = 15;
            int intervalOfMinutes = 5;

            for (int i = allMinutes; i > 0; i = i - intervalOfMinutes) {

                MessageSender.getInstance().sendMessageInChannel(message, "Бот выключиться через " + i + " минут");

                TimeUnit.MINUTES.sleep(5);

            }

        } catch (IllegalArgumentException exception) {
            MessageSender.getInstance().sendMessageInChannel(message, exception.getMessage());
        } catch (InterruptedException exception) {
            MessageSender.getInstance().sendMessageInChannel(message, "Ошибка во время сна таймера");
        }

        MessageSender.getInstance().sendMessageInChannel(message, "Засыпаю");

    }

    public static ShutDownCommand getShutDownCommand() {
        return shutDownCommand;
    }
}
