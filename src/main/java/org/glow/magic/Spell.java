package org.glow.magic;

import org.glow.item.Item;

public abstract class Spell extends Item implements Castable {

    private Element spellElement;
    private String spellInfo;
    private int coastInMana;

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

}
