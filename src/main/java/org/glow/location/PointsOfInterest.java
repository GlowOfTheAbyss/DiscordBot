package org.glow.location;

import java.util.Set;

public abstract class PointsOfInterest extends Location {
    private Set<Action> actions;

    public Set<Action> getActions() {
        return actions;
    }

    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }
}
