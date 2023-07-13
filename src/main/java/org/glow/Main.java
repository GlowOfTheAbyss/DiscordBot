package org.glow;

public class Main {

    private static Systems systems;

    public static void main(String[] args) {

        systems = new Systems();
        systems.start();

    }

    public static Systems getSystems() {
        return systems;
    }
}