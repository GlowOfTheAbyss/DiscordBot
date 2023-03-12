package org.glow.commands;

import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.person.Player;

public abstract class Command implements LaunchedCommand {

    private String name;

    public static final Snowflake admin = Snowflake.of(238764551221280770L);

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /*---*/
    protected Player userToPlayer(Message message) {
        for (Player player : Player.getPlayerList()) {
            if (message.getAuthor().get().getId().equals(player.getSnowflake())) {
                return player;
            }
        }
        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title("Игрок не найден");
        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();
        return null;
    }

    protected boolean isNotAdmin(Player player) {
        return !player.getSnowflake().equals(admin);
    }

    protected boolean lengthCheck(Message message, int lenght) {
        return message.getContent().split(" ").length == lenght;
    }

}
