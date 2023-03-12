package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.commands.Command;
import org.glow.person.Player;

public class StatsCommand extends Command {

    private static final StatsCommand statsCommand = new StatsCommand();

    private StatsCommand() {
        setName("stats");
    }

    @Override
    public void start(Message message) {

        Player player = userToPlayer(message);
        if (player == null) {
            return;
        }

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        builder.title(player.getName());
        builder.description("Локация: " + player.getLocation().getName() + "\n" + "\n"
                + ":pig2: " + player.getCoins() + "\n"
                + "Энергия: " + player.getEnergy() + "\n"
                + "Здоровье: " + player.getHealth() + "\n"
                + "Мана: " + player.getMana() + "\n" + "\n"
                + "Уровень: " + player.getLevel() + "\n"
                + "Боевой уровень: " + player.getCombatLevel() + "\n" + "\n"
                + "Сила: " + player.getStrength() + "\n"
                + "Выносливость: " + player.getEndurance() + "\n"
                + "Ловкость: " + player.getAgility() + "\n"
                + "Интеллект: " + player.getIntelligence() + "\n"
                + "Внимание: " + player.getPerception() + "\n"
                + "Удача: " + player.getLuck() + "\n");

        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();
    }

    public static StatsCommand getStatsCommand() {
        return statsCommand;
    }
}
