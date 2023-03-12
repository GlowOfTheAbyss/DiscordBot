package org.glow.location.region.mondstadt.subareas.pointsofinterest.mondstadt;

import org.glow.location.*;

public class FavoniusCathedral extends PointsOfInterest {

    private static final FavoniusCathedral favoniusCathedral = new FavoniusCathedral();

    private FavoniusCathedral() {
        setName("Собор Барбатоса");
        setImage("https://cdn.discordapp.com/attachments/882560343983915029/1065586700660789319/FavoniusCathedral.png");
    }

    public static FavoniusCathedral getFavoniusCathedral() {
        return favoniusCathedral;
    }

}
