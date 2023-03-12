package org.glow.commands.SystrmCommands;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.Main;
import org.glow.commands.Command;
import org.glow.person.Player;

import java.util.concurrent.TimeUnit;

public class ShotDownCommand extends Command {

    private static final ShotDownCommand shotDownCommand = new ShotDownCommand();

    private ShotDownCommand() {
        setName("shot_down");
    }

    @Override
    public void start(Message message) {

        Player player = userToPlayer(message);
        if (player == null) {
            return;
        }

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        if (isNotAdmin(player)) {
            builder.title("У вас нет прав для этой команды");
            message.getChannel().block().createMessage(builder.build()).block();
            message.delete().block();
            return;
        }

        for (int i = 15; i > 0; i = i - 5) {
            builder.title("Бот выключиться через " + i + " минут");
            message.getChannel().block().createMessage(builder.build()).block();

            try {
                TimeUnit.MINUTES.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        builder.title("Выключаюсь");
        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();
        Main.systems.gateway.logout().block();

    }

    public static ShotDownCommand getShotDownCommand() {
        return shotDownCommand;
    }
}
