package org.glow.location;

import org.glow.location.region.liyue.Liyue;
import org.glow.location.region.liyue.subareas.LiyueHarbor;
import org.glow.location.region.liyue.subareas.TheChasm;
import org.glow.location.region.mondstadt.Mondstadt;
import org.glow.location.region.mondstadt.subareas.CityMondstadt;
import org.glow.location.region.mondstadt.subareas.StarfellValley;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.cityMondstadt.FavoniusCathedral;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.cityMondstadt.SchulzsBlacksmith;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.cityMondstadt.actions.BuyInFavoniusCathedral;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.cityMondstadt.actions.BuyInSchulzsBlacksmith;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.cityMondstadt.actions.HealFavoniusCathedral;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.starfellValley.WhisperingWoods;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Map {

    private static Map map;
    private final List<Location> locations = new ArrayList<>();

    private Map() {}

    public List<Location> getLocations() {
        return locations;
    }

    public Set<Region> getRegions() {

        return Set.of(Liyue.getLiyue(),
                Mondstadt.getMondstadt());

    }

    public Set<Subarea> getSubareas() {

        return Set.of(LiyueHarbor.getLiyueHarbor(),
                TheChasm.getTheChasm(),
                CityMondstadt.getCityMondstadt(),
                StarfellValley.getStarfellValley());

    }

    public static Map getMap() {
        if (map == null) {
            map = new Map();
        }
        return map;
    }

    public void createMap() {
        locations.add(Liyue.getLiyue());
            locations.add(LiyueHarbor.getLiyueHarbor());
            locations.add(TheChasm.getTheChasm());

        locations.add(Mondstadt.getMondstadt());
            locations.add(CityMondstadt.getCityMondstadt());
                locations.add(FavoniusCathedral.getFavoniusCathedral());
                locations.add(SchulzsBlacksmith.getSchulzsBlacksmith());

            locations.add(StarfellValley.getStarfellValley());
                locations.add(WhisperingWoods.getWhisperingWoods());


        initializeActions();
    }

    private void initializeActions() {

        BuyInFavoniusCathedral.getBuyFavoniusCathedral();
        BuyInSchulzsBlacksmith.getBuyInSchulzsBlacksmith();
        HealFavoniusCathedral.getHealFavoniusCathedral();

    }

}
