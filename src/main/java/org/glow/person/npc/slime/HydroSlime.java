package org.glow.person.npc.slime;

import org.glow.person.NPC;
import org.glow.storage.Inventory;
import org.glow.storage.SkillBook;

public class HydroSlime extends NPC {

    public HydroSlime() {

        setName("Гидро слайм");
        setImage("https://cdn.discordapp.com/attachments/1066672288897978439/1087251608884228126/HydroSlime.png");
        setStringSnowflake("0");

        setStrength(0);
        setEndurance(1);
        setAgility(2);
        setIntelligence(0);
        setPerception(1);
        setLuck(0);

        setHealth(40 * getEndurance());
        setMana((int) (0.5 * getIntelligence()));

        setInventory(new Inventory());
        setSkillBook(new SkillBook());

    }

}
