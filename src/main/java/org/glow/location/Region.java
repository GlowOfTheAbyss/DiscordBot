package org.glow.location;

import java.util.HashSet;
import java.util.Set;

public abstract class Region extends Location {

    private Set<Subarea> subareas = new HashSet<>();

    public Set<Subarea> getSubareas() {
        return subareas;
    }

    public void setSubareas(Set<Subarea> subareas) {
        this.subareas = subareas;
    }

}