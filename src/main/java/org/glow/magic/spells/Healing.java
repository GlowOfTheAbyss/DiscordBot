package org.glow.magic.spells;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.fileManager.Save;
import org.glow.magic.Spell;
import org.glow.person.PersonManager;
import org.glow.person.Player;

import java.util.Random;

public class Healing extends Spell {

    public Healing() {
        setSpellName("Исцеление");
        setCoastInMana(50);
        setPrice(6_000);
    }

    @Override
    public void cast(Message message, Player player) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        if (message.getContent().split(" ").length != 3) {
            builder.title("Команда должна иметь вид:");
            builder.title("!cast Исецеление [@Игрок]");
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
        for (Player playerFromList : PersonManager.getInstance().getPlayers()) {
            if (PersonManager.getInstance().getPlayerSnowflake(playerFromList).equals(message.getMemberMentions().get(0).getId())) {
                targetPlayer = playerFromList;
            }
        }
        if (targetPlayer == null) {
            builder.title("Не найдена цель заклинания");
            message.getChannel().block().createMessage(builder.build()).block();
            message.delete().block();
            return;
        }

        int heal = new Random().nextInt(1, 3) + player.getIntelligence();
        heal *= 10;

        player.setMana(player.getMana() - getCoastInMana());
        targetPlayer.setHealth(targetPlayer.getHealth() + heal);
        if (targetPlayer.getHealth() > PersonManager.getInstance().getPlayerMaxHealth(targetPlayer)) {
            targetPlayer.setHealth(PersonManager.getInstance().getPlayerMaxHealth(player));
        }
        Save.getSave().saveFile(player);
        Save.getSave().saveFile(targetPlayer);

        builder.title(PersonManager.getInstance().getPersonName(player) + " востанавил " + heal + " здоровье " + PersonManager.getInstance().getPersonName(targetPlayer));
        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }
}
