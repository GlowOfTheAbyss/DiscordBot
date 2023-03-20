package org.glow.person.npc;

import org.glow.person.NPC;

import java.util.Random;

public class NPCGenerator {

    public NPC levelAdaptation(NPC npc, int playerLevel) {

        int random = new Random().nextInt(playerLevel - 5, playerLevel + 6);
        if (random <= 0) {
            return npc;
        }

        npc.setStrength(random + npc.getStrength());
        npc.setEndurance(random + npc.getEndurance());
        npc.setAgility(random + npc.getAgility());
        npc.setIntelligence(random + npc.getIntelligence());
        npc.setPerception(random + npc.getPerception());
        npc.setLuck(random + npc.getLuck());

        npc.setHealth(npc.getHealth() + (npc.getEndurance() * 10));
        npc.setMana(npc.getMana() + ((int) (0.5 * npc.getIntelligence() * 10)));

        return npc;

    }

}
