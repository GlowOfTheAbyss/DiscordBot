package org.glow.location.region.mondstadt.subareas.pointsofinterest.starfellValley.actions;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.actions.Battle;
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
import org.glow.location.Action;
import org.glow.person.NPC;
import org.glow.person.Player;
import org.glow.person.npc.hilichurl.Hilichurl;
import org.glow.person.npc.hilichurl.HilichurlBerserker;
import org.glow.person.npc.hilichurl.HilichurlFighter;
import org.glow.person.npc.slime.HydroSlime;
import org.glow.person.npc.slime.PyroSlime;

import java.util.List;
import java.util.Random;

public class WhisperingWoodsAdventures extends Action {

    private static WhisperingWoodsAdventures whisperingWoodsAdventures;

    private WhisperingWoodsAdventures() {}

    private static final List<NPC> npcList = List.of(new Hilichurl(),
            new HilichurlBerserker(),
            new HilichurlFighter(),
            new HydroSlime(),
            new PyroSlime());

    @Override
    public void startAction(Message message, Player player) {

        int random = new Random().nextInt(101);
        int chestChance = 1 + ((int) (0.5 * player.getLuck()));
        if (chestChance > 5) {
            chestChance = 5;
        }

        if (chestChance > random) {
            chest(message, player);
        } else {
            enemy(message, player);
        }

    }

    private void enemy(Message message, Player player) {

        NPC randomNPC = npcList.get(new Random().nextInt(npcList.size()));

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title("Вы встречаете противника " + randomNPC.getName());
        builder.image(randomNPC.getImage());
        builder.description(randomNPC.getName() + " HP : " + randomNPC.getHealth()
                + randomNPC.getName() + " боевой уровень : " + randomNPC.getCombatLevel());

        message.getChannel().block().createMessage(builder.build()).block();

        Battle battle = new Battle(message, player, randomNPC);
        battle.start();

    }

    private void chest(Message message, Player player) {

        int random = new Random().nextInt(101);
        int itemChance = 5 + ((int) (0.5 * player.getLuck()));
        if (itemChance > 10) {
            itemChance = 10;
        }

        if (itemChance > random) {
            item(message, player);
        }

        int randomCoins = new Random().nextInt(11) + 4 * player.getLuck() * 10;

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title("Вы нашли сундук");
        builder.description("В сундуке было " + randomCoins  + " :pig2:\n\n"
                +":pig2: " + player.getCoins() + "\n"
                + "Энергия: " + player.getEnergy() + "\n"
                + "Здоровье: " + player.getHealth() + "\n"
                + "Мана: " + player.getMana() + "\n");

        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    private void item(Message message, Player player) {

        List<Item> items = List.of(new DullBlade(), new SilverSword(), new IronShield(),
                new WhiteIronShield(), new IronHeadArmor(), new IronBodyArmor(),
                new IronLegArmor(), new WhiteIronHeadArmor(), new WhiteIronBodyArmor(),
                new WhiteIronLegArmor());

        Item randomItem = items.get(new Random().nextInt(items.size()));

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        if (player.getInventory().getBag().size() >= player.getInventory().getBagSize()) {

            builder.title("Вы нашли предмет " + randomItem.getName());
            builder.description("Но у ва нет места в инвентаре что бы его забрать");

            message.getChannel().block().createMessage(builder.build()).block();

            return;
        }

        player.getInventory().getBag().add(randomItem);
        Save.getSave().saveFile(player);

        builder.title("Вы нашли предмет " + randomItem.getName());
        builder.description(":pig2: " + player.getCoins() + "\n"
                + "Энергия: " + player.getEnergy() + "\n"
                + "Здоровье: " + player.getHealth() + "\n"
                + "Мана: " + player.getMana() + "\n");

        message.getChannel().block().createMessage(builder.build()).block();

    }

    public static WhisperingWoodsAdventures getWhisperingWoodsAdventures() {
        if (whisperingWoodsAdventures == null) {
            whisperingWoodsAdventures = new WhisperingWoodsAdventures();
        }
        return whisperingWoodsAdventures;
    }
}
