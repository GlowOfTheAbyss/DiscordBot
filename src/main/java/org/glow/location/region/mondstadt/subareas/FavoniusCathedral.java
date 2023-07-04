package org.glow.location.region.mondstadt.subareas;

import org.glow.location.Region;
import org.glow.location.region.mondstadt.subareas.actions.BuyInFavoniusCathedral;
import org.glow.location.region.mondstadt.subareas.actions.HealFavoniusCathedral;

import java.util.Set;

public class FavoniusCathedral extends Region {

    private static FavoniusCathedral favoniusCathedral;

    private FavoniusCathedral() {
        setName("Собор Барбатоса");
        setImage("https://cdn.discordapp.com/attachments/882560343983915029/1065586700660789319/FavoniusCathedral.png");

        setRegions(Set.of());
        setActions(Set.of(new HealFavoniusCathedral(null, null),
                new BuyInFavoniusCathedral(null, null)));

    }

    public static FavoniusCathedral getFavoniusCathedral() {
        if (favoniusCathedral == null) {
            favoniusCathedral = new FavoniusCathedral();
        }
        return favoniusCathedral;
    }

}
