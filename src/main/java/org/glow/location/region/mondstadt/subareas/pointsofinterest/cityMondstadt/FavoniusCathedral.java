package org.glow.location.region.mondstadt.subareas.pointsofinterest.cityMondstadt;

import org.glow.location.*;
import org.glow.location.region.mondstadt.subareas.CityMondstadt;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.cityMondstadt.actions.BuyInFavoniusCathedral;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.cityMondstadt.actions.HealFavoniusCathedral;

import java.util.Set;

public class FavoniusCathedral extends PointsOfInterest {

    private static FavoniusCathedral favoniusCathedral;

    private FavoniusCathedral() {
        setName("Собор Барбатоса");
        setImage("https://cdn.discordapp.com/attachments/882560343983915029/1065586700660789319/FavoniusCathedral.png");

        setActions(Set.of(HealFavoniusCathedral.getHealFavoniusCathedral(),
                BuyInFavoniusCathedral.getBuyFavoniusCathedral()));
    }

    public static FavoniusCathedral getFavoniusCathedral() {
        if (favoniusCathedral == null) {
            favoniusCathedral = new FavoniusCathedral();
        }
        return favoniusCathedral;
    }

}
