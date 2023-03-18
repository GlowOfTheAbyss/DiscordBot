package org.glow.location;

import java.util.Set;

public abstract class PointsOfInterest extends Location {

    private Subarea subarea;
    private Set<Action> actions;

    public Subarea getSubarea() {
        return subarea;
    }

    public void setSubarea(Subarea subarea) {
        this.subarea = subarea;
    }

    public Set<Action> getActions() {
        return actions;
    }

    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }
}
