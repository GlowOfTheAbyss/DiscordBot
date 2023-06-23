package org.glow.person.npc.slime;

import org.glow.magic.Element;
import org.glow.person.NPC;
import org.glow.storage.Inventory;
import org.glow.storage.SpellBook;

import java.util.Random;

public class PyroSlime extends NPC {

    public PyroSlime() {

        setName("Пиро слайм");
        setImage("https://cdn.discordapp.com/attachments/1066672288897978439/1087251608389308498/PyroSlime.png");
        setElement(Element.PYRO);

        setSnowflake("0");

        setStrength(new Random().nextInt(1, 4));
        setEndurance(1);
        setAgility(1);
        setIntelligence(0);
        setPerception(0);
        setLuck(0);

        setHealth((4 + getEndurance()) * 10);
        setMana(0);

        setInventory(new Inventory());
        setSpellBook(new SpellBook());

    }

}
