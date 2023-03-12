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

    public Region getRegionFromSubarea(Subarea subarea) {

        if (subarea.getName().equalsIgnoreCase(CityMondstadt.getCityMondstadt().getName())) {
            return Mondstadt.getMondstadt();
        }

        throw new IllegalArgumentException("Region not found");

    }

    public Subarea getSubareaFromPointsOfInterest(PointsOfInterest pointsOfInterest) {

        if (pointsOfInterest.getName().equalsIgnoreCase(FavoniusCathedral.getFavoniusCathedral().getName())) {
            return CityMondstadt.getCityMondstadt();
        }

        if (pointsOfInterest.getName().equalsIgnoreCase(SchulzsBlacksmith.getSchulzsBlacksmith().getName())) {
            return CityMondstadt.getCityMondstadt();
        }

        throw new IllegalArgumentException("Subarea not found");

    }

    public List<Region> getRegions() {

        return List.of(Liyue.getLiyue(),
                Mondstadt.getMondstadt());

    }

    public List<Subarea> getSubareas(Region region) {

        if (region.getName().equalsIgnoreCase(Liyue.getLiyue().getName())) {
            return List.of();
        }

        if (region.getName().equalsIgnoreCase(Mondstadt.getMondstadt().getName())) {
            return List.of(CityMondstadt.getCityMondstadt());
        }

        return null;

    }

    public List<PointsOfInterest> getPointsOfInterests(Subarea subarea) {

        if (subarea.getName().equalsIgnoreCase(CityMondstadt.getCityMondstadt().getName())) {
            return List.of(FavoniusCathedral.getFavoniusCathedral(),
                    SchulzsBlacksmith.getSchulzsBlacksmith());
        }

        return null;
    }

    public List<Action> getActions(PointsOfInterest pointsOfInterest) {

        if (pointsOfInterest.getName().equalsIgnoreCase(FavoniusCathedral.getFavoniusCathedral().getName())) {
            return List.of(BuyFavoniusCathedral.getBuyFavoniusCathedral(),
                    HealFavoniusCathedral.getHealFavoniusCathedral());
        }

        if (pointsOfInterest.getName().equalsIgnoreCase(SchulzsBlacksmith.getSchulzsBlacksmith().getName())) {
            return List.of(BuyInSchulzsBlacksmith.getBuyInSchulzsBlacksmith());
        }

        return null;
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
