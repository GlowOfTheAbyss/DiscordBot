package org.glow.location.region.mondstadt.subareas.pointsofinterest.starfellValley.actions;

import discord4j.core.object.entity.Message;
import org.glow.actions.Battle;
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

    private static List<NPC> npcList = List.of(new Hilichurl(),
            new HilichurlBerserker(),
            new HilichurlFighter(),
            new HydroSlime(),
            new PyroSlime());

    @Override
    public void startAction(Message message, Player player) {

        int random = new Random().nextInt(101);
        int chestChance = 5 + ((int) (0.5 * player.getLuck()));
        if (chestChance > 10) {
            chestChance = 10;
        }

        if (random > chestChance) {
            enemy(message, player);
        } else {
            // chest
        }

    }

    private void enemy(Message message, Player player) {

        NPC randomNPC = npcList.get(new Random().nextInt(npcList.size()));

        Battle battle = new Battle(message, player, randomNPC);
        battle.start();


    }

}
