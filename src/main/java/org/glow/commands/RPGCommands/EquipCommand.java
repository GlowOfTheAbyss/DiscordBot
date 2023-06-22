package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.commands.Command;
import org.glow.fileManager.Save;
import org.glow.item.Item;
import org.glow.person.Player;
import org.glow.storage.InventoryManager;

public class EquipCommand extends Command {

    private static EquipCommand equipCommand;

    private EquipCommand() {
        setName("equip");
        setInfo("""
                команда для экиперовки предмета из инвентаря
                !equip [название предмета]
                """);
    }

    @Override
    public void start(Message message) {

        Player player = userToPlayer(message);
        if (player == null) {
            return;
        }

        if (playerInBattle(player, message)) {
            return;
        }

        String equipItemName = message.getContent().replaceFirst("!equip ", "");

        try {

            Item item = InventoryManager.getInstance().findUnequippedItem(player.getInventory(), equipItemName);

            player.getInventory().getBag().remove(item);
            item.equipItem(player.getInventory());
            Save.getSave().saveFile(player);

            EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
            builder.title("Вы экипировали " + item.getName());
            message.getChannel().block().createMessage(builder.build()).block();
            message.delete().block();

        } catch (RuntimeException exception) {

            errorMessage(message, exception.getMessage());

        }

    }

    public static EquipCommand getEquipCommand() {
        if (equipCommand == null) {
            equipCommand = new EquipCommand();
        }
        return equipCommand;
    }
}
