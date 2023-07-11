package org.glow.person.npc.hilichurl;

import org.glow.magic.Element;
import org.glow.person.npc.NPC;
import org.glow.storage.Inventory;
import org.glow.storage.SpellBook;

import java.util.Random;

public class HilichurlBerserker extends NPC {

    public HilichurlBerserker() {

        setName("Хиличурл-берсерк");
        setImage("https://cdn.discordapp.com/attachments/1066672288897978439/1087251592056684605/HilichurlBerserker.png");
        setElement(Element.PHYSICAL);

        setSnowflake("0");

        setStrength(new Random().nextInt(3, 5));
        setEndurance(1);
        setAgility(1);
        setIntelligence(0);
        setPerception(1);
        setLuck(0);

        setHealth((8 + getEndurance()) * 10);
        setMana(0);

        setInventory(new Inventory());
        setSpellBook(new SpellBook());

    }

}
