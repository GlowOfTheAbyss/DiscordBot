package org.glow.location.region.mondstadt.subareas;

import org.glow.location.Subarea;
import org.glow.location.region.mondstadt.subareas.actions.WhisperingWoodsAdventures;

import java.util.Set;

public class WhisperingWoods extends Subarea {

    private static WhisperingWoods whisperingWoods;

    private WhisperingWoods() {
        setName("Шепчущий лес");
        setImage("https://cdn.discordapp.com/attachments/1066672288897978439/1087118905698435193/WhisperingWoods.png");

        setSubareas(Set.of());
        setActions(Set.of(WhisperingWoodsAdventures.getWhisperingWoodsAdventures()));
    }

    public static WhisperingWoods getWhisperingWoods() {
        if (whisperingWoods == null) {
            whisperingWoods = new WhisperingWoods();
        }
        return whisperingWoods;
    }

}
