package org.glow.location.region.mondstadt.subareas.pointsofinterest.starfellValley;

import org.glow.location.PointsOfInterest;

public class WhisperingWoods extends PointsOfInterest {

    private static WhisperingWoods whisperingWoods;

    private WhisperingWoods() {
        setName("Шепчущий лес");
        setImage("");
    }

    public static WhisperingWoods getWhisperingWoods() {
        if (whisperingWoods == null) {
            whisperingWoods = new WhisperingWoods();
        }
        return whisperingWoods;
    }

}
