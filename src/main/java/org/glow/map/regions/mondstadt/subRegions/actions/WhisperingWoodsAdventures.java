package org.glow.map.regions.mondstadt.subRegions.actions;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.actions.battle.BattleManager;
import org.glow.actions.chests.CommonChest;
import org.glow.actions.chests.ExquisiteChest;
import org.glow.map.location.action.Action;
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

    public WhisperingWoodsAdventures(Message message, Player player) {
        super(message, player);
        setName("Отправиться на поиски монстров | !go");
    }

    @Override
    public void startAction() {

        int random = new Random().nextInt(101);
        int chestChance = 1 + ((int) (0.5 * getPlayer().getLuck()));
        int maxChestChance = 5;
        if (chestChance > maxChestChance) {
            chestChance = maxChestChance;
        }

        if (chestChance >= random) {
            chest();
        } else {
            enemy();
        }

    }

    private void enemy() {

        List<NPC> npcList = List.of(new Hilichurl(),
                new HilichurlFighter(),
                new HilichurlBerserker(),
                new HydroSlime(),
                new PyroSlime());

        NPC randomNPC;

        if (PersonManager.getInstance().getPersonLevel(getPlayer()) < 6) {

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

        getMessage().getChannel().block().createMessage(builder.build()).block();

        BattleManager.getInstance().createBattle(getMessage(), getPlayer(), randomNPC);

    }

    private void chest() {

        int random = new Random().nextInt(101);
        int exquisiteChestChance = 20 + ((int) (0.5 * getPlayer().getLuck()));
        int maxExquisiteChestChance = 40;

        if (exquisiteChestChance > maxExquisiteChestChance) {
            exquisiteChestChance = maxExquisiteChestChance;
        }

        if (exquisiteChestChance >= random) {
            new ExquisiteChest(getMessage(), getPlayer()).openChest();
        } else {
            new CommonChest(getMessage(), getPlayer()).openChest();
        }

    }

}
