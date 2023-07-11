package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import org.glow.Main;
import org.glow.commands.Command;
import org.glow.map.regions.mondstadt.subRegions.FavoniusCathedral;
import org.glow.map.regions.mondstadt.subRegions.actions.HealFavoniusCathedral;
import org.glow.message.MessageSender;
import org.glow.person.PersonManager;
import org.glow.person.Player;

public class HealCommand extends Command {

    private static HealCommand healCommand;

    private HealCommand() {
        setName("heal");
        String info = """
                команда для лечения персонажа в специальных заведениях
                %s%s - показывает подробную информацию о услуге
                %s%s [количество здоровья что нужно востановить]
                """;
        setInfo(String.format(info,
                Main.systems.commandPrefix, getName(),
                Main.systems.commandPrefix, getName()));
    }

    @Override
    public void start(Message message) {

        try {

            Player player = userToPlayer(message);

            if (playerInBattle(player)) {
                MessageSender.getInstance().sendMessageInChannel(message, "Находясь в битве нельзя это использовать");
                return;
            }

            if (PersonManager.getInstance().getPlayerRegion(player) instanceof FavoniusCathedral) {
                new HealFavoniusCathedral(message, player).startAction();
            }

        } catch (IllegalArgumentException exception) {
            MessageSender.getInstance().sendMessageInChannel(message, exception.getMessage());
        }

    }

    public static HealCommand getHealCommand() {
        if (healCommand == null) {
            healCommand = new HealCommand();
        }
        return healCommand;
    }
}
