package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import org.glow.Main;
import org.glow.commands.Command;
import org.glow.fileManager.Save;
import org.glow.item.Armor;
import org.glow.item.Item;
import org.glow.item.Weapon;
import org.glow.message.MessageSender;
import org.glow.person.Player;
import org.glow.storage.InventoryManager;

public class EquipCommand extends Command {

    private static EquipCommand equipCommand;

    private EquipCommand() {
        setName("equip");
        String info = """
                команда для экиперовки предмета из инвентаря
                %s%s [название предмета]
                """;
        setInfo(String.format(info,
                Main.getSystems().getCommandPrefix(), getName()));
    }

    @Override
    public void start(Message message) {

        try {

            Player player = userToPlayer(message);

            if (playerInBattle(player)) {
                MessageSender.getInstance().sendMessageInChannel(message, "Находясь в битве нельзя это использовать");
                return;
            }

            String equipItemName = message.getContent().replaceFirst("!equip ", "");
            Item item = InventoryManager.getInstance().findUnequippedItem(player.getInventory(), equipItemName);

            player.getInventory().getBag().remove(item);

            if (item instanceof Armor) {
                ((Armor) item).equipItem(player.getInventory());
            } else if (item instanceof Weapon) {
                ((Weapon) item).equipItem(player.getInventory());
            }
            Save.getSave().saveFile(player);

            String title = "Вы экипировали " + item.getName();
            MessageSender.getInstance().sendMessageInChannel(message, title);

        } catch (IllegalArgumentException exception) {
            MessageSender.getInstance().sendMessageInChannel(message, exception.getMessage());
        }

    }

    public static EquipCommand getEquipCommand() {
        if (equipCommand == null) {
            equipCommand = new EquipCommand();
        }
        return equipCommand;
    }
}
