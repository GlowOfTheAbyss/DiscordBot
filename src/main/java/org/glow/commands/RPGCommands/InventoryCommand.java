package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import org.glow.commands.Command;
import org.glow.message.MessageSender;
import org.glow.message.TextManager;
import org.glow.person.PersonManager;
import org.glow.person.Player;

public class InventoryCommand extends Command {

    private static InventoryCommand inventoryCommand;

    private InventoryCommand() {
        setName("inventory");
        setInfo("""
                показывает инвентарь персонажа
                """);
    }

    @Override
    public void start(Message message) {

        try {

            Player player = userToPlayer(message);

            String title = "Инвентарь " + PersonManager.getInstance().getPersonName(player);

            String description = """
                    %s
                    
                    %s
                    """;

            description = String.format(description,
                    TextManager.getInstance().getPlayerEquippedItems(player),
                    TextManager.getInstance().getPlayerInventory(player));

            MessageSender.getInstance().sendMessageInChannel(message, title, description);

        } catch (IllegalArgumentException exception) {
            MessageSender.getInstance().sendMessageInChannel(message, exception.getMessage());
        }

    }

    public static InventoryCommand getInventoryCommand() {
        if (inventoryCommand == null) {
            inventoryCommand = new InventoryCommand();
        }
        return inventoryCommand;
    }
}
