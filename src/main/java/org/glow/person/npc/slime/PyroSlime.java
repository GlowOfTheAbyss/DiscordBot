package org.glow.person.npc.slime;

import org.glow.person.NPC;
import org.glow.storage.Inventory;
import org.glow.storage.SkillBook;

public class PyroSlime extends NPC {

    public PyroSlime() {

        setName("Пиро слайм");
        setImage("https://cdn.discordapp.com/attachments/1066672288897978439/1087251608389308498/PyroSlime.png");
        setSnowflake("0");

        setStrength(0);
        setEndurance(1);
        setAgility(2);
        setIntelligence(0);
        setPerception(1);
        setLuck(0);

        setHealth((4 + getEndurance()) * 10);
        setMana(((int) (0.5 * getIntelligence())) * 10);

        setInventory(new Inventory());
        setSkillBook(new SkillBook());

    }

}
