package org.glow.location.region.mondstadt.subareas;

import org.glow.location.Region;
import org.glow.location.region.mondstadt.Mondstadt;

import java.util.Set;

public class CityMondstadt extends Region {

    private static CityMondstadt cityMondstadt;

    private CityMondstadt() {
        setName("Город Мондштадт");
        setImage("https://cdn.discordapp.com/attachments/882560343983915029/1065586686500802661/Mondstadt.png");

        setRegions(Set.of(FavoniusCathedral.getFavoniusCathedral(),
                SchulzsBlacksmith.getSchulzsBlacksmith()));
        setActions(Set.of());

    }

    public static CityMondstadt getCityMondstadt() {
        if (cityMondstadt == null) {
            cityMondstadt = new CityMondstadt();
        }
        return cityMondstadt;
    }

}
