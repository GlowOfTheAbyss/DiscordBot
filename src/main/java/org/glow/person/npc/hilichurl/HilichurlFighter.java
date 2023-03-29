package org.glow.person.npc.hilichurl;

import org.glow.person.NPC;
import org.glow.storage.Inventory;
import org.glow.storage.SkillBook;

public class HilichurlFighter extends NPC {

    public HilichurlFighter() {

        setName("Хиличурл-боец");
        setImage("https://cdn.discordapp.com/attachments/1066672288897978439/1087251591188447363/HilichurlFighter.png");
        setStringSnowflake("0");

        setStrength(1);
        setEndurance(2);
        setAgility(2);
        setIntelligence(0);
        setPerception(1);
        setLuck(0);

        setHealth(80 * (getEndurance() * 10));
        setMana(20 + ((int) (0.5 * getIntelligence()) * 10));

        setInventory(new Inventory());
        setSkillBook(new SkillBook());

    }

}