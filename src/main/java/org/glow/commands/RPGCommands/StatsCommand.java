package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.commands.Command;
import org.glow.person.PersonManager;
import org.glow.person.Player;

public class StatsCommand extends Command {

    private static StatsCommand statsCommand;

    private StatsCommand() {
        setName("stats");
        setInfo("показывает статистику персонажа");
    }

    @Override
    public void start(Message message) {

        Player player = userToPlayer(message);
        if (player == null) {
            return;
        }

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        builder.title(PersonManager.getInstance().getPersonName(player));
        builder.description("Локация: " + player.getLocationName() + "\n" + "\n"

                + "Мора " + player.getCoins() + "\n"
                + "Энергия: " + player.getEnergy() + "\n"
                + "Здоровье: " + player.getHealth() + "\n"
                + "Мана: " + player.getMana() + "\n" + "\n"

                + "Уровень: " + PersonManager.getInstance().getPersonLevel(player) + "\n"
                + "Боевой уровень: " + PersonManager.getInstance().getPersonCombatLevel(player) + "\n" + "\n"

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
        if (statsCommand == null) {
            statsCommand = new StatsCommand();
        }
        return statsCommand;
    }
}
