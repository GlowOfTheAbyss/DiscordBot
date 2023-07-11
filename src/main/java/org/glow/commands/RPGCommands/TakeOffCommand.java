package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import org.glow.Main;
import org.glow.commands.Command;
import org.glow.fileManager.Save;
import org.glow.item.Item;
import org.glow.message.MessageSender;
import org.glow.person.PersonManager;
import org.glow.person.Player;
import org.glow.storage.InventoryManager;

public class TakeOffCommand extends Command {

    private static TakeOffCommand takeOffCommand;

    private TakeOffCommand() {
        setName("take_off");
        String info = """
                команда для снятия экипированного предмета в инвентарь
                %s%s [имя предмета который необходимо снять]
                """;
        setInfo(String.format(info,
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

            if (player.getInventory().getBag().size() == player.getInventory().getBagSize()) {
                MessageSender.getInstance().sendMessageInChannel(message, PersonManager.getInstance().getPersonName(player) + " ваша сумка заполнена");
                return;
            }

            String itemName = message.getContent().replaceFirst(Main.systems.commandPrefix + getName(), "");
            Item item = InventoryManager.getInstance().findUnequippedItem(player.getInventory(), itemName);
            item.equipItem(player.getInventory());
            Save.getSave().saveFile(player);

            String title = "Вы сняли " + item.getName();
            MessageSender.getInstance().sendMessageInChannel(message, title);

        } catch (IllegalArgumentException exception) {
            MessageSender.getInstance().sendMessageInChannel(message, exception.getMessage());
        }

    }

    public static TakeOffCommand getTakeOffCommand() {
        if (takeOffCommand == null) {
            takeOffCommand = new TakeOffCommand();
        }
        return takeOffCommand;
    }

}
