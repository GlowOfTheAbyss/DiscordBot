package org.glow.storage;

import org.glow.magic.Spell;

import java.util.ArrayList;
import java.util.List;

public class SpellBook {

    private List<Spell> listSpell = new ArrayList<>();

    private int listSpellSize = 10;

    public List<Spell> getListSpell() {
        return listSpell;
    }

    public void setListSpell(List<Spell> listSpell) {
        this.listSpell = listSpell;
    }

    public void addListSpell(Spell spell) {
        listSpell.add(spell);
    }

    public void removeListSpell(Spell spell) {
        listSpell.remove(spell);
    }

    public int getListSpellSize() {
        return listSpellSize;
    }

    public void setListSpellSize(int listSpellSize) {
        this.listSpellSize = listSpellSize;
    }

}
