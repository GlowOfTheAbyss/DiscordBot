package org.glow.location;

import java.util.HashSet;
import java.util.Set;

public abstract class Subarea extends Location {

    private Set<Subarea> subareas = new HashSet<>();

    private Set<Action> actions = new HashSet<>();

    public Set<Subarea> getSubareas() {
        return subareas;
    }

    public void setSubareas(Set<Subarea> subareas) {
        this.subareas = subareas;
    }

    public Set<Action> getActions() {
        return actions;
    }

    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }

}
