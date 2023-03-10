package org.glow.magic.spells;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.fileManager.Save;
import org.glow.magic.Magic;
import org.glow.person.Player;

import java.util.Random;

public class Healing extends Magic {

    public Healing() {
        setSpellName("Исцеление");
        setCoastInMana(50);
        setPrice(6_000);
    }

    @Override
    public void cast(Message message, Player player) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        if (message.getContent().split(" ").length != 3) {
            builder.title("Неверное количество аргументов");
            message.getChannel().block().createMessage(builder.build()).block();
            message.delete().block();
            return;
        }

        if (player.getMana() < getCoastInMana()) {
            builder.title("Не хватает маны");
            message.getChannel().block().createMessage(builder.build()).block();
            message.delete().block();
            return;
        }

        Player targetPlayer = null;
        for (Player playerFromList : Player.getPlayerList()) {
            if (playerFromList.getSnowflake().equals(message.getMemberMentions().get(0).getId())) {
                targetPlayer = playerFromList;
            }
        }
        if (targetPlayer == null) {
            builder.title("Не найдена цель заклинания");
            message.getChannel().block().createMessage(builder.build()).block();
            message.delete().block();
            return;
        }

        int heal = (1 + new Random().nextInt(2) + player.getIntelligence()) * 10;

        player.setMana(player.getMana() - getCoastInMana());
        targetPlayer.setHealth(targetPlayer.getHealth() + heal);
        if (targetPlayer.getHealth() > targetPlayer.getMaxHealth()) {
            targetPlayer.setHealth(targetPlayer.getMaxHealth());
        }
        Save.getSave().saveFile(player);
        Save.getSave().saveFile(targetPlayer);

        builder.title(player.getName() + " востанавил " + heal + " здоровье " + targetPlayer.getName());
        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }
}
