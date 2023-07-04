package org.glow.location.region.mondstadt.subareas.actions;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.Main;
import org.glow.commands.RPGCommands.HealCommand;
import org.glow.fileManager.Save;
import org.glow.location.Action;
import org.glow.location.region.mondstadt.subareas.FavoniusCathedral;
import org.glow.message.Parameters;
import org.glow.message.TextManager;
import org.glow.person.PersonManager;
import org.glow.person.Player;

public class HealFavoniusCathedral extends Action {

    public HealFavoniusCathedral(Message message, Player player) {
        super(message, player);
        setName(Main.systems.commandPrefix + HealCommand.getHealCommand().getName());
        setDesctiption("Востановить здоровье за мору");
    }

    @Override
    public void startAction() {

        if (getMessage().getContent().split(" ").length == 1) {

            createInfo();

        } else {

            heal();

        }

    }

    private void createInfo() {

        String description = """
                **Вы можете заплатить, что бы вас полечили:**
                
                %s %s = %s %s
                """;

        sendMessageInChannel(FavoniusCathedral.getFavoniusCathedral().getName(), String.format(description,
                10, Parameters.COINS, 10, Parameters.HEALTH));

    }

    private void heal() {

        if (getMessage().getContent().split(" ").length != 2) {
            sendMessageInChannel("""
                    Неверное количество аргументов
                    Ожидается что будет: !heal [число]
                    """);
            return;
        }

        int healCoast;
        try {
            healCoast = Integer.parseInt(getMessage().getContent().split(" ")[1]);
        } catch (NumberFormatException exception) {
            sendMessageInChannel("Не найдено число");
            return;
        }

        if (getPlayer().getCoins() < healCoast) {
            sendMessageInChannel(PersonManager.getInstance().getPersonName(getPlayer()) + " у вас недостаточно моры");
            return;
        }

        getPlayer().setCoins(getPlayer().getCoins() - healCoast);
        getPlayer().setHealth(getPlayer().getHealth() + (healCoast));
        if (getPlayer().getHealth() > PersonManager.getInstance().getPlayerMaxHealth(getPlayer())) {
            getPlayer().setHealth(PersonManager.getInstance().getPlayerMaxHealth(getPlayer()));
        }
        Save.getSave().saveFile(getPlayer());

        String title = PersonManager.getInstance().getPersonName(getPlayer()) + " востановил " + healCoast + " поинтов здоровья";
        sendMessageInChannel(title, TextManager.getInstance().getPlayerParameters(getPlayer()));

    }

}
