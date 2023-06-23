package org.glow.person.npc.hilichurl;

import org.glow.magic.Element;
import org.glow.person.NPC;
import org.glow.storage.Inventory;
import org.glow.storage.SpellBook;

import java.util.Random;

public class Hilichurl extends NPC {

    public Hilichurl() {

        setName("Хиличурл");
        setImage("https://cdn.discordapp.com/attachments/1066672288897978439/1087251591637241886/Hilichurl.png");
        setElement(Element.PHYSICAL);

        setSnowflake("0");

        setStrength(new Random().nextInt(1, 3));
        setEndurance(1);
        setAgility(new Random().nextInt(2, 4));
        setIntelligence(0);
        setPerception(1);
        setLuck(0);

        setHealth((6 + getEndurance()) * 10);
        setMana(0);

        setInventory(new Inventory());
        setSpellBook(new SpellBook());

    }

}
