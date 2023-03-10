package org.glow.location.region.mondstadt.subareas;

import org.glow.location.Subarea;

public class CityMondstadt extends Subarea {

    private static final String name = "Город Мондштадт";
    private static final String image = "https://cdn.discordapp.com/attachments/882560343983915029/1065586686500802661/Mondstadt.png";
    private static final CityMondstadt cityMondstadt = new CityMondstadt(name, image);

    public CityMondstadt(String name, String image) {
        super(name, image);
    }

    public static CityMondstadt getCityMondstadt() {
        return cityMondstadt;
    }

}
