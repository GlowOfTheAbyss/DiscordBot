package org.glow.map.regions.mondstadt.subRegions.actions;

import discord4j.core.object.entity.Message;
import org.glow.Main;
import org.glow.commands.RPGCommands.HealCommand;
import org.glow.fileManager.Save;
import org.glow.map.location.action.Action;
import org.glow.map.regions.mondstadt.subRegions.FavoniusCathedral;
import org.glow.message.MessageSender;
import org.glow.message.Parameters;
import org.glow.message.TextManager;
import org.glow.person.PersonManager;
import org.glow.person.Player;

public class HealFavoniusCathedral extends Action {

    public HealFavoniusCathedral(Message message, Player player) {
        super(message, player);
        setName(Main.getSystems().getCommandPrefix() + HealCommand.getHealCommand().getName());
        setDescription("Востановить здоровье за мору");
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

        int price = 10;
        int heal = 10;

        String description = """
                **Вы можете заплатить, что бы вас полечили:**
                
                %s %s = %s %s
                """;
        description = String.format(description,
                price, Parameters.COINS.getName(), heal, Parameters.HEALTH.getName());

        MessageSender.getInstance().sendMessageInChannel(getMessage(),
                FavoniusCathedral.getFavoniusCathedral().getName(), description);

    }

    private void heal() {

        if (getMessage().getContent().split(" ").length != 2) {
            MessageSender.getInstance().sendMessageInChannel(getMessage(),
                    """
                    Неверное количество аргументов
                    Ожидается что будет: !heal [число]
                    """);
            return;
        }

        int healCoast;
        try {
            healCoast = Integer.parseInt(getMessage().getContent().split(" ")[1]);
        } catch (NumberFormatException exception) {
            MessageSender.getInstance().sendMessageInChannel(getMessage(), "Не найдено число");
            return;
        }

        if (getPlayer().getCoins() < healCoast) {
            MessageSender.getInstance().sendMessageInChannel(getMessage(), PersonManager.getInstance().getPersonName(getPlayer()) + " у вас недостаточно моры");
            return;
        }

        getPlayer().setCoins(getPlayer().getCoins() - healCoast);
        getPlayer().setHealth(getPlayer().getHealth() + (healCoast));
        if (getPlayer().getHealth() > PersonManager.getInstance().getPlayerMaxHealth(getPlayer())) {
            getPlayer().setHealth(PersonManager.getInstance().getPlayerMaxHealth(getPlayer()));
        }
        Save.getSave().saveFile(getPlayer());

        String title = PersonManager.getInstance().getPersonName(getPlayer()) + " востановил " + healCoast + " поинтов здоровья";
        MessageSender.getInstance().sendMessageInChannel(getMessage(), title, TextManager.getInstance().getPlayerParameters(getPlayer()));

    }

}
