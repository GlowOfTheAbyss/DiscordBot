package org.glow.message;

public enum Parameters {

    HEALTH("Здоровье"),

    MANA("Мана"),

    ENERGY("Энергия"),

    COINS("Мора");

    private final String name;

    Parameters(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
