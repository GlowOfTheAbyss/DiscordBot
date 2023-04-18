package org.glow.actions;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.fileManager.Save;
import org.glow.item.Item;
import org.glow.item.body.IronBodyArmor;
import org.glow.item.body.WhiteIronBodyArmor;
import org.glow.item.head.IronHeadArmor;
import org.glow.item.head.WhiteIronHeadArmor;
import org.glow.item.lefthand.IronShield;
import org.glow.item.lefthand.WhiteIronShield;
import org.glow.item.legs.IronLegArmor;
import org.glow.item.legs.WhiteIronLegArmor;
import org.glow.item.righthand.DullBlade;
import org.glow.item.righthand.SilverSword;
import org.glow.person.Player;

import java.util.List;
import java.util.Random;

public class Chests {

    private static Chests chests;

    private Chests() {}

    public void getCommonChest(Message message, Player player) {

        String title = player.getName() + " находит обычный сундук";

        int randomCoins = 2 + new Random().nextInt(4) + player.getLuck();
        randomCoins *= 10;

        player.setCoins(player.getCoins() + randomCoins);
        Save.getSave().saveFile(player);

        standardMessage(message, player, title, randomCoins);

    }

    public void getExquisiteChest(Message message, Player player) {

        String title = player.getName() + " находит богатый сундук";

        int random = new Random().nextInt(101);
        int itemChance = 5 + ((int) (0.5 * player.getLuck()));
        if (itemChance > 10) {
            itemChance = 10;
        }

        if (itemChance >= random) {

            if (player.getInventory().getBag().size() >= player.getInventory().getBagSize()) {
                fullInventoryMessage(message, player);
            } else {
                exquisiteChestItem(message, player);
            }

        }

        int randomCoins = 5 + new Random().nextInt(8) + (2 * player.getLuck());
        randomCoins *= 10;

        player.setCoins(player.getCoins() + randomCoins);
        Save.getSave().saveFile(player);

        standardMessage(message, player, title, randomCoins);

    }

    private void exquisiteChestItem(Message message, Player player) {

        List<Item> items = List.of(new DullBlade(), new SilverSword(), new IronShield(),
                new WhiteIronShield(), new IronHeadArmor(), new IronBodyArmor(),
                new IronLegArmor(), new WhiteIronHeadArmor(), new WhiteIronBodyArmor(),
                new WhiteIronLegArmor());

        Item randomItem = items.get(new Random().nextInt(items.size()));

        player.getInventory().getBag().add(randomItem);
        Save.getSave().saveFile(player);

        itemFindMessage(message, player, randomItem);

    }

    public void getPreciousChest(Message message, Player player) {

        // add later

    }

    public void getLuxuriousChest(Message message, Player player) {

        // add later

    }

    private void standardMessage(Message message, Player player, String title, int coins) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title(title);
        builder.description(player.getName() + " находит " + coins + " :pig2:\n\n"
                + ":pig2: " + player.getCoins() + "\n"
                + "Энергия: " + player.getEnergy() + "\n"
                + "Здоровье: " + player.getHealth() + "\n"
                + "Мана: " + player.getMana() + "\n");

        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    private void itemFindMessage(Message message, Player player, Item item) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title(player.getName() + " нашел " + item.getName());
        builder.description(item.getName() + " добавлен в инвентарь");

        message.getChannel().block().createMessage(builder.build()).block();

    }
    
    private void fullInventoryMessage(Message message, Player player) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title(player.getName() + " находит предмет, но у него нет места в инвентаре что бы его забрать");
        builder.description("Предмет остается в сундуке");

        message.getChannel().block().createMessage(builder.build()).block();
        
    }

    public static Chests getChests() {
        if (chests == null) {
            chests = new Chests();
        }
        return chests;
    }
}
