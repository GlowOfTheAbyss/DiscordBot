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
import org.glow.item.head.Head;
import org.glow.item.head.NoneHead;
import org.glow.item.lefthand.LeftHand;
import org.glow.item.lefthand.NoneLeftHand;
import org.glow.item.legs.Legs;
import org.glow.item.legs.NoneLegs;
import org.glow.item.neck.Neck;
import org.glow.item.neck.NoneNeck;
import org.glow.item.righthand.NoneRightHand;
import org.glow.item.righthand.RightHand;
import org.glow.person.Player;

import java.util.List;

public class TakeOffCommand extends Command {

    private static final TakeOffCommand takeOffCommand = new TakeOffCommand();;

    private TakeOffCommand() {
        setName("take_off");
    }

    @Override
    public void start(Message message) {
        Player player = userToPlayer(message);
        if (player == null) {
            return;
        }

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        if (player.getInventory().getBag().size() == player.getInventory().getBagSize()) {
            builder.title(player.getName() + " ваша сумка заполнена");
            message.getChannel().block().createMessage(builder.build()).block();
            message.delete().block();
            return;
        }

        String takeOffItemName = message.getContent().replaceFirst("!unequip ", "");
        List<Item> equipList = player.getInventory().getEquippedItems();

        for (Item item : equipList) {

            if (item.getName().equalsIgnoreCase(takeOffItemName)) {

                takeOff(player, item);
                Save.getSave().saveFile(player);

                builder.title("Вы сняли " + item.getName());
                message.getChannel().block().createMessage(builder.build()).block();
                message.delete().block();
                return;

            }

        }

        builder.title("У вас не экипирован " + takeOffItemName);
        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    private void takeOff(Player player, Item item) {

        if (item instanceof Body) {
            player.getInventory().setBody(new NoneBody());
        }
        if (item instanceof Finger) {
            if (player.getInventory().getRightFinger().getName().equalsIgnoreCase(item.getName())) {
                player.getInventory().setRightFinger(new NoneFinger());
            }
            if (player.getInventory().getLeftFinger().getName().equalsIgnoreCase(item.getName())) {
                player.getInventory().setLeftFinger(new NoneFinger());
            }
        }
        if (item instanceof Head) {
            player.getInventory().setHead(new NoneHead());
        }
        if (item instanceof LeftHand) {
            player.getInventory().setLeftHand(new NoneLeftHand());
        }
        if (item instanceof Legs) {
            player.getInventory().setLegs(new NoneLegs());
        }
        if (item instanceof Neck) {
            player.getInventory().setNeck(new NoneNeck());
        }
        if (item instanceof RightHand) {
            player.getInventory().setRightHand(new NoneRightHand());
        }

        player.getInventory().getBag().add(item);

    }

    public static TakeOffCommand getTakeOffCommand() {
        return takeOffCommand;
    }

}
