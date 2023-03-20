package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.commands.Command;
import org.glow.item.Item;
import org.glow.person.Player;

public class InventoryCommand extends Command {

    private static InventoryCommand inventoryCommand;

    private InventoryCommand() {
        setName("inventory");
    }

    @Override
    public void start(Message message) {

        Player player = userToPlayer(message);
        if (player == null) {
            return;
        }

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        builder.title("Инвентарь " + player.getName());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Сумка: ");

        if (player.getInventory().getBag().isEmpty()) {
            stringBuilder.append("Пусто");
        } else {
            for (Item items : player.getInventory().getBag()) {
                stringBuilder.append(items.getName()).append(" ");
            }
        }

        builder.description("Броня: " + player.getInventory().getArmor() + "\n"
                + "Атака: " + player.getInventory().getAttack() + "\n" + "\n"

                + "Голова: " +player.getInventory().getHead().getName() + "\n"
                + "Тело: " + player.getInventory().getBody().getName() + "\n"
                + "Ноги: " + player.getInventory().getLegs().getName() + "\n"
                + "Правая рука: " + player.getInventory().getRightHand().getName() + "\n"
                + "Левая рука: " + player.getInventory().getLeftHand().getName() + "\n" + "\n"

                + "Шея: " + player.getInventory().getNeck().getName() + "\n"
                + "Палец правой руки: " + player.getInventory().getRightFinger().getName() + "\n"
                + "Палец левой руки: " + player.getInventory().getLeftFinger().getName() + "\n" + "\n"

                + stringBuilder);

        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    public static InventoryCommand getInventoryCommand() {
        if (inventoryCommand == null) {
            inventoryCommand = new InventoryCommand();
        }
        return inventoryCommand;
    }
}
