package org.glow.location;

public abstract class Action implements LaunchedAction {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
