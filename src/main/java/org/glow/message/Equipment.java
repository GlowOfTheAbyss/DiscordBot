package org.glow.message;

public enum Equipment {

    HEAD("Голова"),
    BODY("Тело"),
    LEGS("Ноги"),
    RIGHT_HAND("Правая рука"),
    LEFT_HAND("Левая рука"),
    NECK("Шея"),
    FINGER("Палец"),

    ATTACK("Атака"),
    DEFEND("Защита");

    private final String name;

    Equipment(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
