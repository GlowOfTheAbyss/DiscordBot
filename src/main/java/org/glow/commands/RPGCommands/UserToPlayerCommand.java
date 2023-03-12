package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.commands.Command;
import org.glow.fileManager.Save;
import org.glow.person.Player;

public class UserToPlayerCommand extends Command {

    private static final UserToPlayerCommand userToPlayerCommand = new UserToPlayerCommand();

    private UserToPlayerCommand() {
        setName("add");
    }

    @Override
    public void start(Message message) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        for (Player player : Player.getPlayerList()) {
            if (player.getSnowflake().equals(message.getAuthor().get().getId())) {
                builder.title("Такой игрок уже существует");
                message.getChannel().block().createMessage(builder.build()).block();
                message.delete().block();
                return;
            }
        }

        Player newPlayer = new Player(message.getAuthor().get().getId());
        Save.getSave().saveFile(newPlayer);

        builder.title("Игрок: " + newPlayer.getName() + " успешно создан");
        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    public static UserToPlayerCommand getUserToPlayerCommand() {
        return userToPlayerCommand;
    }
}
