package org.glow.location;

import java.util.HashSet;
import java.util.Set;

public abstract class Region extends Location {

    private Set<Region> regions = new HashSet<>();

    private Set<Action> actions = new HashSet<>();

    private Region headRegion;

    public Set<Region> getRegions() {
        return regions;
    }

    public void setRegions(Set<Region> regions) {
        this.regions = regions;
    }

    public Set<Action> getActions() {
        return actions;
    }

    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }

    public Region getHeadRegion() {
        return headRegion;
    }

    public void setHeadRegion(Region headRegion) {
        this.headRegion = headRegion;
    }
}