package org.glow.person.npc.hilichurl;

import org.glow.person.NPC;
import org.glow.storage.Inventory;
import org.glow.storage.SpellBook;

public class HilichurlBerserker extends NPC {

    public HilichurlBerserker() {

        setName("Хиличурл-берсерк");
        setImage("https://cdn.discordapp.com/attachments/1066672288897978439/1087251592056684605/HilichurlBerserker.png");
        setSnowflake("0");

        setStrength(2);
        setEndurance(3);
        setAgility(3);
        setIntelligence(0);
        setPerception(2);
        setLuck(0);

        setHealth((10 + getEndurance()) * 10);
        setMana((2 + (int) (0.5 * getIntelligence())) * 10);

        setInventory(new Inventory());
        setSpellBook(new SpellBook());

    }

}
