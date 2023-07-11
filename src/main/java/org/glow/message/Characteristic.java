package org.glow.message;

public enum Characteristic {

    STRENGTH("Сила"),
    ENDURANCE("Выносливость"),
    AGILITY("Ловкость"),
    INTELLIGENCE("Интеллект"),
    PERCEPTION("Внимание"),
    LUCK("Удача");

    private final String name;

    Characteristic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
