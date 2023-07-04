package org.glow.message;

import org.glow.person.Player;

public enum Characteristic {

    STRENGTH("Сила"),
    ENDURANCE("Выносливость"),
    AGILITY("Ловкость"),
    INTELLIGENCE("Интеллект"),
    PERCEPTION("Внимание"),
    LUCK("Удача"),

    ATTACK("Атака"),
    DEFEND("Защита");

    private final String name;

    Characteristic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
