package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.commands.Command;
import org.glow.fileManager.Save;
import org.glow.item.Items;
import org.glow.item.body.Body;
import org.glow.item.lefthand.LeftHand;
import org.glow.item.righthand.RightHand;
import org.glow.item.head.Head;
import org.glow.item.legs.Legs;
import org.glow.person.Player;

public class EquipCommand extends Command {

    private static final EquipCommand equipCommand = new EquipCommand();

    private EquipCommand() {
        setName("equip");
    }

    @Override
    public void start(Message message) {

        Player player = userToPlayer(message);
        if (player == null) {
            return;
        }

        String equipItemName = message.getContent().replaceFirst("!equip ", "");

        for (Items items : player.getInventory().getBag()) {

            if (equipItemName.equalsIgnoreCase(items.getName())) {

                if (Head.class.isAssignableFrom(items.getClass())) {
                    if (player.getInventory().getHead().getName().equalsIgnoreCase("Пусто")) {
                        player.getInventory().setHead((Head) items);
                        player.getInventory().removeFromBag(items);
                        Save.getSave().saveFile(player);
                        equipMessage(message, items);
                    } else {
                        if (inventoryFull(player)) {
                            equipErrorMessage(message);
                            return;
                        }

                        player.getInventory().addToBag(player.getInventory().getHead());
                        player.getInventory().setHead((Head) items);
                        Save.getSave().saveFile(player);
                        equipMessage(message, items);
                    }
                    return;
                }

                if (Body.class.isAssignableFrom(items.getClass())) {
                    if (player.getInventory().getBody().getName().equalsIgnoreCase("Пусто")) {
                        player.getInventory().setBody((Body) items);
                        player.getInventory().removeFromBag(items);
                        Save.getSave().saveFile(player);
                        equipMessage(message, items);
                    } else {
                        if (inventoryFull(player)) {
                            equipErrorMessage(message);
                            return;
                        }

                        player.getInventory().addToBag(player.getInventory().getBody());
                        player.getInventory().setBody((Body) items);
                        Save.getSave().saveFile(player);
                        equipMessage(message, items);
                    }
                    return;
                }

                if (Legs.class.isAssignableFrom(items.getClass())) {
                    if (player.getInventory().getLegs().getName().equalsIgnoreCase("Пусто")) {
                        player.getInventory().setLegs((Legs) items);
                        player.getInventory().removeFromBag(items);
                        Save.getSave().saveFile(player);
                        equipMessage(message, items);
                    } else {
                        if (inventoryFull(player)) {
                            equipErrorMessage(message);
                            return;
                        }

                        player.getInventory().addToBag(player.getInventory().getLegs());
                        player.getInventory().setLegs((Legs) items);
                        Save.getSave().saveFile(player);
                        equipMessage(message, items);
                    }
                    return;
                }

                if (RightHand.class.isAssignableFrom(items.getClass())) {
                    if (player.getInventory().getRightHand().getName().equalsIgnoreCase("Пусто")) {
                        player.getInventory().setRightHand((RightHand) items);
                        player.getInventory().removeFromBag(items);
                        Save.getSave().saveFile(player);
                        equipMessage(message, items);
                    } else {
                        if (inventoryFull(player)) {
                            equipErrorMessage(message);
                            return;
                        }

                        player.getInventory().addToBag(player.getInventory().getRightHand());
                        player.getInventory().setRightHand((RightHand) items);
                        Save.getSave().saveFile(player);
                        equipMessage(message, items);
                    }
                    return;
                }

                if (LeftHand.class.isAssignableFrom(items.getClass())) {
                    if (player.getInventory().getLeftHand().getName().equalsIgnoreCase("Пусто")) {
                        player.getInventory().setLeftHand((LeftHand) items);
                        player.getInventory().removeFromBag(items);
                        Save.getSave().saveFile(player);
                        equipMessage(message, items);
                    } else {
                        if (inventoryFull(player)) {
                            equipErrorMessage(message);
                            return;
                        }

                        player.getInventory().addToBag(player.getInventory().getLeftHand());
                        player.getInventory().setLeftHand((LeftHand) items);
                        Save.getSave().saveFile(player);
                        equipMessage(message, items);
                    }
                    return;
                }
            }
        }

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title("Предмет не найден");
        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    private boolean inventoryFull(Player player) {
        return player.getInventory().getBag().size() == 10;
    }

    private void equipMessage(Message message, Items item) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title("Вы экипировали " + item.getName());

        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    private void equipErrorMessage(Message message) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title("Слот для этого предмета уже занят, а инвентарь полон");
        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    public static EquipCommand getEquipCommand() {
        return equipCommand;
    }
}
