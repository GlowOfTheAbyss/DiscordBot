package org.glow.location;

import org.glow.location.region.liyue.Liyue;
import org.glow.location.region.liyue.subareas.LiyueHarbor;
import org.glow.location.region.liyue.subareas.MtTianheng;
import org.glow.location.region.liyue.subareas.TheChasm;
import org.glow.location.region.mondstadt.Mondstadt;
import org.glow.location.region.mondstadt.subareas.CityMondstadt;
import org.glow.location.region.mondstadt.subareas.FavoniusCathedral;
import org.glow.location.region.mondstadt.subareas.SchulzsBlacksmith;
import org.glow.location.region.mondstadt.subareas.WhisperingWoods;

import java.util.HashSet;
import java.util.Set;

public class Map {

    private static Map map;
    private final Set<Location> locations = new HashSet<>();
    private final Set<Location> liyueLocations = new HashSet<>();
    private final Set<Location> mondstadtLocations = new HashSet<>();
    private final Set<Location> cityMondstadtLocations = new HashSet<>();

    private Map() {}

    public Set<Location> getLocations() {
        return locations;
    }

    public Set<Location> getLiyueLocations() {
        return liyueLocations;
    }

    public Set<Location> getMondstadtLocations() {
        return mondstadtLocations;
    }

    public Set<Location> getCityMondstadtLocations() {
        return cityMondstadtLocations;
    }

    public Set<Region> getRegions() {

        return Set.of(Liyue.getLiyue(),
                Mondstadt.getMondstadt());

    }

    public static Map getInstance() {
        if (map == null) {
            map = new Map();
        }
        return map;
    }

    public void createMap() {

        locations.add(Liyue.getLiyue());

        liyueLocations.add(LiyueHarbor.getLiyueHarbor());
        liyueLocations.add(MtTianheng.getMtTianheng());
        liyueLocations.add(TheChasm.getTheChasm());
        locations.addAll(liyueLocations);

        locations.add(Mondstadt.getMondstadt());

        mondstadtLocations.add(CityMondstadt.getCityMondstadt());
            cityMondstadtLocations.add(FavoniusCathedral.getFavoniusCathedral());
            cityMondstadtLocations.add(SchulzsBlacksmith.getSchulzsBlacksmith());
            locations.addAll(cityMondstadtLocations);
        mondstadtLocations.add(WhisperingWoods.getWhisperingWoods());

        locations.addAll(mondstadtLocations);

    }

}
