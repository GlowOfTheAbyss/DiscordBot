package org.glow.person.npc.slime;

import org.glow.magic.Element;
import org.glow.person.npc.NPC;
import org.glow.storage.Inventory;
import org.glow.storage.SpellBook;

import java.util.Random;

public class HydroSlime extends NPC {

    public HydroSlime() {

        setName("Гидро слайм");
        setImage("https://cdn.discordapp.com/attachments/1066672288897978439/1087251608884228126/HydroSlime.png");
        setElement(Element.HYDRO);

        setSnowflake("0");

        setStrength(0);
        setEndurance(new Random().nextInt(2, 5));
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
