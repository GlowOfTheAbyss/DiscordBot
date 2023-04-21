package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.commands.Command;
import org.glow.fileManager.Save;
import org.glow.person.PersonManager;
import org.glow.person.Player;

public class UserToPlayerCommand extends Command {

    private static UserToPlayerCommand userToPlayerCommand;

    private UserToPlayerCommand() {
        setName("add");
        setInfo("добавляет пользователя как нового игрока");
    }

    @Override
    public void start(Message message) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        for (Player player : PersonManager.getInstance().getPlayers()) {
            if (player.getSnowflake().equals(message.getAuthor().get().getId())) {
                builder.title("Такой игрок уже существует");
                message.getChannel().block().createMessage(builder.build()).block();
                message.delete().block();
                return;
            }
        }

        for (Player player : PersonManager.getInstance().getPlayers()) {
            System.out.println(PersonManager.getInstance().getPersonName(player));
        }

        Player newPlayer = new Player(message.getAuthor().get().getId());
        Save.getSave().saveFile(newPlayer);

        builder.title("Игрок: " + PersonManager.getInstance().getPersonName(newPlayer) + " успешно создан");
        builder.description("Используй !help");
        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    public static UserToPlayerCommand getUserToPlayerCommand() {
        if (userToPlayerCommand == null) {
            userToPlayerCommand = new UserToPlayerCommand();
        }
        return userToPlayerCommand;
    }
}
