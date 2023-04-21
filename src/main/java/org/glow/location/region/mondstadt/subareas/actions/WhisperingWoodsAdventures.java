package org.glow.location.region.mondstadt.subareas.actions;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.actions.Chests;
import org.glow.actions.battle.BattleManager;
import org.glow.fileManager.Save;
import org.glow.location.Action;
import org.glow.person.NPC;
import org.glow.person.PersonManager;
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

    private WhisperingWoodsAdventures() {
        setName("Отправиться на поиски монстров | !go");
    }

    @Override
    public void startAction(Message message, Player player) {

        int random = new Random().nextInt(101);
        int chestChance = 1 + ((int) (0.5 * player.getLuck()));
        if (chestChance > 5) {
            chestChance = 5;
        }

        if (chestChance >= random) {
            chest(message, player);
        } else {
            enemy(message, player);
        }

    }

    private void enemy(Message message, Player player) {

        List<NPC> npcList = List.of(new Hilichurl(),
                new HilichurlBerserker(),
                new HilichurlFighter(),
                new HydroSlime(),
                new PyroSlime());

        NPC randomNPC;

        if (PersonManager.getInstance().getPersonLevel(player) < 6) {

            int random = new Random().nextInt(2);
            if (random == 0) {
                randomNPC = new HydroSlime();
            } else {
                randomNPC = new PyroSlime();
            }

        } else {
            randomNPC = npcList.get(new Random().nextInt(npcList.size()));
        }

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title("Вы встречаете противника " + randomNPC.getName());
        builder.image(randomNPC.getImage());
        builder.description(randomNPC.getName() + " HP : " + randomNPC.getHealth() + "\n"
                + randomNPC.getName() + " боевой уровень : " + PersonManager.getInstance().getPersonCombatLevel(randomNPC));

        message.getChannel().block().createMessage(builder.build()).block();

        BattleManager.getInstance().createBattle(message, player, randomNPC);

    }

    private void chest(Message message, Player player) {

        player.setEnergy(player.getEnergy() + 1);
        Save.getSave().saveFile(player);

        int random = new Random().nextInt(101);
        int exquisiteChestChance = 20 + ((int) (0.5 * player.getLuck()));
        if (exquisiteChestChance > 40) {
            exquisiteChestChance = 40;
        }

        if (exquisiteChestChance >= random) {
            Chests.getChests().getExquisiteChest(message, player);
        } else {
            Chests.getChests().getCommonChest(message, player);
        }

    }

    public static WhisperingWoodsAdventures getWhisperingWoodsAdventures() {
        if (whisperingWoodsAdventures == null) {
            whisperingWoodsAdventures = new WhisperingWoodsAdventures();
        }
        return whisperingWoodsAdventures;
    }
}
