package org.glow.activities.chests;

import discord4j.core.object.entity.Message;
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
import org.glow.person.PersonManager;
import org.glow.person.Player;

import java.util.List;
import java.util.Random;

public class ExquisiteChest extends Chest {

    private final int baseItemChance = 5;
    private final int maxItemChance = 10;

    public ExquisiteChest(Message message, Player player) {
        super(message, player);
    }

    @Override
    public void openChest() {

        String title = PersonManager.getInstance().getPersonName(getPlayer()) + " находит богатый сундук";

        int random = new Random().nextInt(101);
        int itemChance = baseItemChance + ((int) (0.5 * getPlayer().getLuck()));
        if (itemChance > maxItemChance) {
            itemChance = maxItemChance;
        }

        if (itemChance >= random) {
            exquisiteChestItem();
        }

        int randomCoins = new Random().nextInt(11, 16) + getPlayer().getLuck();
        randomCoins *= 10;

        getPlayer().setCoins(getPlayer().getCoins() + randomCoins);
        Save.getSave().saveFile(getPlayer());

        coinsFindMessage(title, randomCoins);

    }

    private void exquisiteChestItem() {

        List<Item> items = List.of(new DullBlade(), new SilverSword(), new IronShield(),
                new WhiteIronShield(), new IronHeadArmor(), new IronBodyArmor(),
                new IronLegArmor(), new WhiteIronHeadArmor(), new WhiteIronBodyArmor(),
                new WhiteIronLegArmor());

        Item randomItem = items.get(new Random().nextInt(items.size()));

        if (getPlayer().getInventory().getBag().size() >= getPlayer().getInventory().getBagSize()) {
            fullInventoryMessage(randomItem);
            return;
        }

        getPlayer().getInventory().getBag().add(randomItem);
        Save.getSave().saveFile(getPlayer());

        itemFindMessage(randomItem);

    }

}
