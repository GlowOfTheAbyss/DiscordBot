package org.glow.location;

import org.glow.location.region.liyue.Liyue;
import org.glow.location.region.mondstadt.Mondstadt;
import org.glow.location.region.mondstadt.subareas.CityMondstadt;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.mondstadt.FavoniusCathedral;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.mondstadt.SchulzsBlacksmith;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.mondstadt.actions.BuyFavoniusCathedral;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.mondstadt.actions.BuyInSchulzsBlacksmith;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.mondstadt.actions.HealFavoniusCathedral;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private static final Map map = new Map();
    private static final List<Location> locations = new ArrayList<>();

    private Map() {}

    public static List<Location> getLocations() {
        return locations;
    }

    public List<Region> getRegions() {

        return List.of(Liyue.getLiyue(),
                Mondstadt.getMondstadt());

    }

    public Region getRegionFromSubarea(Subarea subarea) {

        if (subarea instanceof CityMondstadt) {
            return Mondstadt.getMondstadt();
        }

        throw new IllegalArgumentException("Region not found");
    }

    public Subarea getSubareaFromPointsOfInterest(PointsOfInterest pointsOfInterest) {

        if (pointsOfInterest instanceof FavoniusCathedral
                || pointsOfInterest instanceof SchulzsBlacksmith) {
            return CityMondstadt.getCityMondstadt();
        }

        throw new IllegalArgumentException("Subarea not found");

    }

    public List<Subarea> getSubareas(Region region) {

        if (region instanceof Liyue) {
            return List.of();
        }

        if (region instanceof Mondstadt) {
            return List.of(CityMondstadt.getCityMondstadt());
        }

        throw new IllegalArgumentException("List Subarea not found");

    }

    public List<PointsOfInterest> getPointsOfInterests(Subarea subarea) {

        if (subarea instanceof CityMondstadt) {
            return List.of(FavoniusCathedral.getFavoniusCathedral(),
                    SchulzsBlacksmith.getSchulzsBlacksmith());
        }

        throw new IllegalArgumentException("List Points of interest  not found");
    }

    public List<Action> getActions(PointsOfInterest pointsOfInterest) {

        if (pointsOfInterest instanceof FavoniusCathedral) {
            return List.of(BuyFavoniusCathedral.getBuyFavoniusCathedral(),
                    HealFavoniusCathedral.getHealFavoniusCathedral());
        }

        if (pointsOfInterest instanceof SchulzsBlacksmith) {
            return List.of(BuyInSchulzsBlacksmith.getBuyInSchulzsBlacksmith());
        }

        throw new IllegalArgumentException("List action  not found");
    }

    public static Map getMap() {
        return map;
    }

    public static void createMap() {
        locations.add(Liyue.getLiyue());

        locations.add(Mondstadt.getMondstadt());
            locations.add(CityMondstadt.getCityMondstadt());
                locations.add(FavoniusCathedral.getFavoniusCathedral());
                locations.add(SchulzsBlacksmith.getSchulzsBlacksmith());
    }

}
