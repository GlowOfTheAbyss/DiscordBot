package org.glow.location.region.mondstadt.subareas;

import org.glow.location.Subarea;

public class CityMondstadt extends Subarea {

    private static final CityMondstadt cityMondstadt = new CityMondstadt();

    private CityMondstadt() {
        setName("Город Мондштадт");
        setImage("https://cdn.discordapp.com/attachments/882560343983915029/1065586686500802661/Mondstadt.png");
    }

    public static CityMondstadt getCityMondstadt() {
        return cityMondstadt;
    }

}
