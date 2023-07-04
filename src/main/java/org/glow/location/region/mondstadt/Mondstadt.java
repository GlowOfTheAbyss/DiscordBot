package org.glow.location.region.mondstadt;

import org.glow.location.LocationManager;
import org.glow.location.Region;
import org.glow.location.region.mondstadt.subareas.CityMondstadt;
import org.glow.location.region.mondstadt.subareas.WhisperingWoods;

import java.util.Set;

public class Mondstadt extends Region {

    private static Mondstadt mondstadt;

    private Mondstadt() {
        setName("Мондштадт");
        setImage("https://cdn.discordapp.com/attachments/1066672288897978439/1086592288416866304/Mondstadt.webp");

        setRegions(Set.of(CityMondstadt.getCityMondstadt(),
                WhisperingWoods.getWhisperingWoods()));
        setActions(Set.of());

    }

    public static Mondstadt getMondstadt() {
        if (mondstadt == null) {
            mondstadt = new Mondstadt();
        }
        return mondstadt;
    }

}
