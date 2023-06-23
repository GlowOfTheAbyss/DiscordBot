package org.glow.magic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import discord4j.core.object.entity.Message;
import org.glow.person.Player;

public abstract class Spell implements Castable {

    private String spellName;
    private Element spellElement;
    private String spellInfo;
    private int coastInMana;
    private int price;

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }

    public Element getSpellElement() {
        return spellElement;
    }

    public void setSpellElement(Element spellElement) {
        this.spellElement = spellElement;
    }

    public String getSpellInfo() {
        return spellInfo;
    }

    public void setSpellInfo(String spellInfo) {
        this.spellInfo = spellInfo;
    }

    public int getCoastInMana() {
        return coastInMana;
    }

    public void setCoastInMana(int coastInMana) {
        this.coastInMana = coastInMana;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
