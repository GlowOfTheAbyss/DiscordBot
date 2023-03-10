package org.glow.location.region.mondstadt.subareas.pointsofinterest.mondstadt;

import org.glow.location.*;

public class FavoniusCathedral extends PointsOfInterest {

    private static final String name = "Собор Барбатоса";
    private static final String image = "https://cdn.discordapp.com/attachments/882560343983915029/1065586700660789319/FavoniusCathedral.png";
    private static final FavoniusCathedral favoniusCathedral = new FavoniusCathedral(name, image);

    public FavoniusCathedral(String name, String image) {
        super(name, image);
    }

    public static FavoniusCathedral getFavoniusCathedral() {
        return favoniusCathedral;
    }

}
