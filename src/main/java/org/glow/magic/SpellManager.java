package org.glow.magic;

import org.glow.magic.spells.Dawn;
import org.glow.magic.spells.ShiningMiracle;

import java.util.ArrayList;
import java.util.List;

public class SpellManager {

    private static SpellManager spellManager;

    private final List<Spell> spellList = new ArrayList<>();

    private SpellManager() {
        spellList.add(new ShiningMiracle());
        spellList.add(new Dawn());
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
