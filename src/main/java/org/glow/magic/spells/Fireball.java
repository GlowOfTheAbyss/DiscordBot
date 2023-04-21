package org.glow.magic.spells;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.actions.battle.Battle;
import org.glow.actions.battle.BattleManager;
import org.glow.fileManager.Save;
import org.glow.magic.Magic;
import org.glow.person.Person;
import org.glow.person.PersonManager;
import org.glow.person.Player;

import java.util.Random;

public class Fireball extends Magic {

    public Fireball() {
        setSpellName("Огненый_шар");
        setCoastInMana(10);
        setPrice(0);
    }

    @Override
    public void cast(Message message, Player player) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        if (player.getMana() < getCoastInMana()) {
            builder.title("Не хватает маны");
            message.getChannel().block().createMessage(builder.build()).block();
            message.delete().block();
            return;
        }

        Battle thisBattle = null;
        for (Battle battle : BattleManager.getInstance().getBattles()) {
            if (battle.getParticipants().contains(player)) {
                thisBattle = battle;
            }
        }

        if (thisBattle == null) {
            builder.title("Это заклинание работает только в бою");
            message.getChannel().block().createMessage(builder.build()).block();
            message.delete().block();
            return;
        }

        Person enemy = null;
        for (Person participant : thisBattle.getParticipants()) {
            if (!(participant == player)) {
                enemy = participant;
            }
        }

        if (enemy == null) {
            builder.title("Противник не найден");
            message.getChannel().block().createMessage(builder.build()).block();
            message.delete().block();
            return;
        }

        int damage = new Random().nextInt(5, 11) + player.getIntelligence();

        player.setMana(player.getMana() - getCoastInMana());
        enemy.setHealth(enemy.getHealth() - damage);
        Save.getSave().saveFile(player);

        builder.title(PersonManager.getInstance().getPersonName(player) + " кастует в " + PersonManager.getInstance().getPersonName(enemy) + " огненный шар");
        builder.description("Нанесено " + damage + " урона\n\n"
                + PersonManager.getInstance().getPersonName(player) + "HP : " + player.getHealth() + "\n"
                + PersonManager.getInstance().getPersonName(enemy) + "HP : " + enemy.getHealth());

        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }
}
