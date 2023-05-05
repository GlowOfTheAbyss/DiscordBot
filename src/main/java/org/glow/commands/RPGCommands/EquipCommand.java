package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.commands.Command;
import org.glow.fileManager.Save;
import org.glow.item.Item;
import org.glow.item.body.Body;
import org.glow.item.body.NoneBody;
import org.glow.item.finger.Finger;
import org.glow.item.finger.NoneFinger;
import org.glow.item.head.NoneHead;
import org.glow.item.lefthand.LeftHand;
import org.glow.item.lefthand.NoneLeftHand;
import org.glow.item.legs.NoneLegs;
import org.glow.item.neck.Neck;
import org.glow.item.neck.NoneNeck;
import org.glow.item.righthand.NoneRightHand;
import org.glow.item.righthand.RightHand;
import org.glow.item.head.Head;
import org.glow.item.legs.Legs;
import org.glow.person.Player;

public class EquipCommand extends Command {

    private static EquipCommand equipCommand;

    private EquipCommand() {
        setName("equip");
        setInfo("команда для экиперовки предмета из инвентаря\n" +
                "!equip [название предмета]");
    }

    @Override
    public void start(Message message) {

        Player player = userToPlayer(message);
        if (player == null) {
            return;
        }

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        if (playerInBattle(player)) {
            builder.title("Находясь в битве нельзя это использовать");
            message.getChannel().block().createMessage(builder.build()).block();
            message.delete().block();
            return;
        }

        String equipItemName = message.getContent().replaceFirst("!equip ", "");

        for (Item item : player.getInventory().getBag()) {

            if (equipItemName.equalsIgnoreCase(item.getName())) {

                equip(player, item);
                Save.getSave().saveFile(player);

                builder.title("Вы экипировали " + item.getName());
                message.getChannel().block().createMessage(builder.build()).block();
                message.delete().block();

                return;
            }
        }

        builder.title("Предмет не найден");
        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    private void equip(Player player, Item item) {

        if (item instanceof Body) {
            Item equippedItem = player.getInventory().getBody();
            player.getInventory().setBody((Body) item);
            player.getInventory().getBag().remove(item);
            if (!(equippedItem.getName().equalsIgnoreCase(new NoneBody().getName()))) {
                player.getInventory().getBag().add(equippedItem);
            }
        } else if (item instanceof Finger) {
            if (player.getInventory().getRightFinger().getName().equalsIgnoreCase(new NoneFinger().getName())) {
                player.getInventory().setRightFinger((Finger) item);
            } else if (player.getInventory().getLeftFinger().getName().equalsIgnoreCase(new NoneFinger().getName())) {
                player.getInventory().setRightFinger((Finger) item);
            } else {
                Finger equippedItemRight = player.getInventory().getRightFinger();
                Item equippedItemLeft = player.getInventory().getLeftFinger();
                player.getInventory().setRightFinger((Finger) item);
                player.getInventory().setLeftFinger(equippedItemRight);
                player.getInventory().getBag().remove(item);
                player.getInventory().getBag().add(equippedItemLeft);
            }
        } else if (item instanceof Head) {
            Item equippedItem = player.getInventory().getHead();
            player.getInventory().setHead((Head) item);
            player.getInventory().getBag().remove(item);
            if (!(equippedItem.getName().equalsIgnoreCase(new NoneHead().getName()))) {
                player.getInventory().getBag().add(equippedItem);
            }
        } else if (item instanceof LeftHand) {
            Item equippedItem = player.getInventory().getLeftHand();
            player.getInventory().setLeftHand((LeftHand) item);
            player.getInventory().getBag().remove(item);
            if (!(equippedItem.getName().equalsIgnoreCase(new NoneLeftHand().getName()))) {
                player.getInventory().getBag().add(equippedItem);
            }
        } else if (item instanceof Legs) {
            Item equippedItem = player.getInventory().getLegs();
            player.getInventory().setLegs((Legs) item);
            player.getInventory().getBag().remove(item);
            if (!(equippedItem.getName().equalsIgnoreCase(new NoneLegs().getName()))) {
                player.getInventory().getBag().add(equippedItem);
            }
        } else if (item instanceof Neck) {
            Item equippedItem = player.getInventory().getNeck();
            player.getInventory().setNeck((Neck) item);
            player.getInventory().getBag().remove(item);
            if (!(equippedItem.getName().equalsIgnoreCase(new NoneNeck().getName()))) {
                player.getInventory().getBag().add(equippedItem);
            }
        } else if (item instanceof RightHand) {
            Item equippedItem = player.getInventory().getRightHand();
            player.getInventory().setRightHand((RightHand) item);
            player.getInventory().getBag().remove(item);
            if (!(equippedItem.getName().equalsIgnoreCase(new NoneRightHand().getName()))) {
                player.getInventory().getBag().add(equippedItem);
            }
        }

    }

    public static EquipCommand getEquipCommand() {
        if (equipCommand == null) {
            equipCommand = new EquipCommand();
        }
        return equipCommand;
    }
}
