package org.glow.map.regions.mondstadt.subRegions;

import org.glow.map.regions.Region;
import org.glow.map.regions.mondstadt.subRegions.actions.WhisperingWoodsAdventures;

import java.util.Set;

public class WhisperingWoods extends Region {

    private static WhisperingWoods whisperingWoods;

    private WhisperingWoods() {
        setName("Шепчущий лес");
        setImage("https://cdn.discordapp.com/attachments/1066672288897978439/1087118905698435193/WhisperingWoods.png");

        setRegions(Set.of());
        setActions(Set.of(new WhisperingWoodsAdventures(null, null)));

    }

    public static WhisperingWoods getWhisperingWoods() {
        if (whisperingWoods == null) {
            whisperingWoods = new WhisperingWoods();
        }
        return whisperingWoods;
    }

}
