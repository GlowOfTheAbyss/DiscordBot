package org.glow.activities.mine;

import discord4j.core.object.entity.Message;
import org.glow.activities.chests.CommonChest;
import org.glow.activities.chests.ExquisiteChest;
import org.glow.activities.mine.deposit.IronDeposit;
import org.glow.activities.mine.deposit.WhiteIronDeposit;
import org.glow.person.Player;

import java.util.Random;

public class LightMine extends Mine {

    public LightMine(Message message, Player player) {
        super(message, player);
    }

    @Override
    public void mine() {

        int random = new Random().nextInt(101);
        int chestChance = 3 + getPlayer().getLuck();
        int maxChestChance = 10;
        if (chestChance > maxChestChance) {
            chestChance = maxChestChance;
        }

        if (random < chestChance) {
            chest();
        } else {
            deposit();
        }

    }

    private void chest() {

        int random = new Random().nextInt(101);
        int exquisiteChestChance = 5 + (int) (0.5 * getPlayer().getLuck());
        int maxChestChance = 10;
        if (exquisiteChestChance > maxChestChance) {
            exquisiteChestChance = maxChestChance;
        }

        if (random < exquisiteChestChance) {
            new ExquisiteChest(getMessage(), getPlayer()).openChest();
        } else {
            new CommonChest(getMessage(), getPlayer()).openChest();
        }

    }

    private void deposit() {

        int random = new Random().nextInt(101);
        int whiteIronChance = 10;

        if (random < whiteIronChance) {
            new WhiteIronDeposit(getMessage(), getPlayer()).find();
        } else {
            new IronDeposit(getMessage(), getPlayer()).find();
        }

    }

}
