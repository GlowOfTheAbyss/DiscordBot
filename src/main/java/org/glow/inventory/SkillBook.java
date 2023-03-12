package org.glow.inventory;

import org.glow.magic.Magic;

import java.util.ArrayList;
import java.util.List;

public class SkillBook {

    private List<Magic> listSpell = new ArrayList<>();

    private int listSpellSize = 10;

    public List<Magic> getListSpell() {
        return listSpell;
    }

    public void setListSpell(List<Magic> listSpell) {
        this.listSpell = listSpell;
    }

    public void addListSpell(Magic magic) {
        listSpell.add(magic);
    }

    public void removeListSpell(Magic magic) {
        listSpell.remove(magic);
    }

    public int getListSpellSize() {
        return listSpellSize;
    }

    public void setListSpellSize(int listSpellSize) {
        this.listSpellSize = listSpellSize;
    }

}
