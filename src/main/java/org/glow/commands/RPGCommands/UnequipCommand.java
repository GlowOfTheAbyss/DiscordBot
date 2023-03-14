package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.commands.Command;
import org.glow.fileManager.Save;
import org.glow.item.Items;
import org.glow.person.Player;

import java.util.List;

public class UnequipCommand extends Command {

    private static final UnequipCommand unequipCommand = new UnequipCommand();;

    private UnequipCommand() {
        setName("unequip");
    }

    @Override
    public void start(Message message) {
        Player player = userToPlayer(message);
        if (player == null) {
            return;
        }
        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        String unequipItemName = message.getContent().replaceFirst("!unequip ", "");
        List<Items> equipList = player.getInventory().getEquippedItems();

        for (Items items : equipList) {

            if (items.getName().equalsIgnoreCase(unequipItemName)) {

                if (player.getInventory().getBag().size() == player.getInventory().getBagSize()) {
                    builder.title(player.getName() + " ваша сумка заполнена");
                    message.getChannel().block().createMessage(builder.build()).block();
                    message.delete().block();
                    return;
                }

                player.getInventory().setHead(new NoneArmor());
                player.getInventory().addToBag(items);
                Save.getSave().saveFile(player);

                builder.title("Вы сняли " + items.getName());
                message.getChannel().block().createMessage(builder.build()).block();
                message.delete().block();
                return;

            }

        }

        builder.title("У вас не экипирован " + unequipItemName);
        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    public static UnequipCommand getUnequipCommand() {
        return unequipCommand;
    }
}
