package org.glow.location.region.mondstadt.subareas;

import org.glow.location.Subarea;
import org.glow.location.region.mondstadt.Mondstadt;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.cityMondstadt.FavoniusCathedral;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.cityMondstadt.SchulzsBlacksmith;

import java.util.Set;

public class CityMondstadt extends Subarea {

    private static CityMondstadt cityMondstadt;

    private CityMondstadt() {
        setName("Город Мондштадт");
        setImage("https://cdn.discordapp.com/attachments/882560343983915029/1065586686500802661/Mondstadt.png");

        setPointsOfInterests(Set.of(FavoniusCathedral.getFavoniusCathedral(),
                SchulzsBlacksmith.getSchulzsBlacksmith()));
    }

    public static CityMondstadt getCityMondstadt() {
        if (cityMondstadt == null) {
            cityMondstadt = new CityMondstadt();
        }
        return cityMondstadt;
    }

}
