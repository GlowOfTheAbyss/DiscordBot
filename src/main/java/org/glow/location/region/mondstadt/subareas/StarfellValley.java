package org.glow.location.region.mondstadt.subareas;

import org.glow.location.Subarea;
import org.glow.location.region.mondstadt.Mondstadt;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.starfellValley.WhisperingWoods;

import java.util.Set;

public class StarfellValley extends Subarea {

    private static StarfellValley starfellValley;

    private StarfellValley() {
        setName("Долина звездопадов");
        setImage("");

        setPointsOfInterests(Set.of(WhisperingWoods.getWhisperingWoods()));
    }

    public static StarfellValley getStarfellValley() {
        if (starfellValley == null) {
            starfellValley = new StarfellValley();
        }
        return starfellValley;
    }

}
