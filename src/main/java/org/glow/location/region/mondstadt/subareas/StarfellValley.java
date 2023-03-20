package org.glow.location.region.mondstadt.subareas;

import org.glow.location.Subarea;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.starfellValley.WhisperingWoods;

import java.util.Set;

public class StarfellValley extends Subarea {

    private static StarfellValley starfellValley;

    private StarfellValley() {
        setName("Долина звездопадов");
        setImage("https://cdn.discordapp.com/attachments/1066672288897978439/1087234584137183322/StarfellValley.png");

        setPointsOfInterests(Set.of(WhisperingWoods.getWhisperingWoods()));
    }

    public static StarfellValley getStarfellValley() {
        if (starfellValley == null) {
            starfellValley = new StarfellValley();
        }
        return starfellValley;
    }

}
