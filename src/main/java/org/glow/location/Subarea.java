package org.glow.location;

import java.util.HashSet;
import java.util.Set;

public abstract class Subarea extends Location {
    private Set<PointsOfInterest> pointsOfInterests = new HashSet<>();

    public Set<PointsOfInterest> getPointsOfInterests() {
        return pointsOfInterests;
    }

    public void setPointsOfInterests(Set<PointsOfInterest> pointsOfInterests) {
        this.pointsOfInterests = pointsOfInterests;
    }
}
