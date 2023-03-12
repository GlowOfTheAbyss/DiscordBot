package org.glow.magic;

import org.glow.magic.spells.Healing;

import java.util.ArrayList;
import java.util.List;

public class Spells {

    private static final Spells spells = new Spells();

    private final List<Magic> magicList = new ArrayList<>();

    private Spells() {
        magicList.add(new Healing());
    }

    public static Spells getSpells() {
        return spells;
    }

    public List<Magic> getMagicList() {
        return magicList;
    }

}
