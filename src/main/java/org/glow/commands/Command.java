package org.glow.commands;

import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.actions.battle.Battle;
import org.glow.actions.battle.BattleManager;
import org.glow.person.PersonManager;
import org.glow.person.Player;

import java.nio.channels.Channel;

public abstract class Command implements LaunchedCommand {

    private String name;
    private String info;

    public static final Snowflake admin = Snowflake.of(238764551221280770L);

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    /*---*/
    protected Player userToPlayer(Message message) {
        for (Player player : PersonManager.getInstance().getPlayers()) {
            if (message.getAuthor().get().getId().equals(PersonManager.getInstance().getPlayerSnowflake(player))) {
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
        return !PersonManager.getInstance().getPlayerSnowflake(player).equals(admin);
    }

    protected boolean lengthCheck(Message message, int length) {
        return message.getContent().split(" ").length == length;
    }

    protected void notEnoughEnergyMessage(Message message) {
        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title("Не достаточно энергии");
        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();
    }

    protected boolean playerInBattle(Player player, Message message) {
        for (Battle battle : BattleManager.getInstance().getBattles()) {
            if (battle.getParticipants().contains(player)) {

                EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
                builder.title("Находясь в битве нельзя это использовать");
                message.getChannel().block().createMessage(builder.build()).block();
                message.delete().block();

                return true;
            }
        }
        return false;
    }

    protected void sendMessageInChanel(Message message, String title) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title(title);

        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    protected void sendMessageInChanel(Message message, String title, String description) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title(title);
        builder.description(description);

        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    protected void sendMessageInChanel(Message message, String title, String description, String image) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title(title);
        builder.image(image);
        builder.description(description);

        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

}
