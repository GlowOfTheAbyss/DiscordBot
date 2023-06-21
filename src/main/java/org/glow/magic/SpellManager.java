package org.glow.magic;

import org.glow.magic.spells.Fireball;
import org.glow.magic.spells.Healing;

import java.util.ArrayList;
import java.util.List;

public class SpellManager {

    private static SpellManager spellManager;

    private final List<Spell> spellList = new ArrayList<>();

    private SpellManager() {
        spellList.add(new Healing());
        spellList.add(new Fireball());
    }

    public static SpellManager getInstance() {
        if (spellManager == null) {
            spellManager = new SpellManager();
        }
        return spellManager;
    }

    public List<Spell> getMagicList() {
        return spellList;
    }

}
