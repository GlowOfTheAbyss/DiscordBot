package org.glow.location;

import java.util.HashSet;
import java.util.Set;

public abstract class Subarea extends Location {

    private Region region;
    private Set<PointsOfInterest> pointsOfInterests = new HashSet<>();

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Set<PointsOfInterest> getPointsOfInterests() {
        return pointsOfInterests;
    }

    public void setPointsOfInterests(Set<PointsOfInterest> pointsOfInterests) {
        this.pointsOfInterests = pointsOfInterests;
    }
}
