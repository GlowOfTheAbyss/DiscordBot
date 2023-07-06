package org.glow.map.location;

import org.glow.map.regions.Region;
import org.glow.map.regions.liyue.Liyue;
import org.glow.map.regions.liyue.subRegions.LiyueHarbor;
import org.glow.map.regions.liyue.subRegions.MtTianheng;
import org.glow.map.regions.liyue.subRegions.TheChasm;
import org.glow.map.regions.mondstadt.Mondstadt;
import org.glow.map.regions.mondstadt.subRegions.CityMondstadt;
import org.glow.map.regions.mondstadt.subRegions.FavoniusCathedral;
import org.glow.map.regions.mondstadt.subRegions.SchulzsBlacksmith;
import org.glow.map.regions.mondstadt.subRegions.WhisperingWoods;

import java.util.HashSet;
import java.util.Set;

public class LocationManager {

    private static LocationManager locationManager;
    private final Set<Region> headsRegions = Set.of(Mondstadt.getMondstadt(),
            Liyue.getLiyue());

    private final Set<Region> regions = new HashSet<>();

    private LocationManager() {

        regions.add(Liyue.getLiyue());

            regions.add(LiyueHarbor.getLiyueHarbor());
            regions.add(MtTianheng.getMtTianheng());
            regions.add(TheChasm.getTheChasm());

        regions.add(Mondstadt.getMondstadt());

            regions.add(CityMondstadt.getCityMondstadt());
                regions.add(FavoniusCathedral.getFavoniusCathedral());
                regions.add(SchulzsBlacksmith.getSchulzsBlacksmith());
            regions.add(WhisperingWoods.getWhisperingWoods());

    }

    public void generate() {

        LiyueHarbor.getLiyueHarbor().setHeadRegion(Liyue.getLiyue());
        MtTianheng.getMtTianheng().setHeadRegion(Liyue.getLiyue());
        TheChasm.getTheChasm().setHeadRegion(Liyue.getLiyue());

        CityMondstadt.getCityMondstadt().setHeadRegion(Mondstadt.getMondstadt());
            FavoniusCathedral.getFavoniusCathedral().setHeadRegion(CityMondstadt.getCityMondstadt());
            SchulzsBlacksmith.getSchulzsBlacksmith().setHeadRegion(CityMondstadt.getCityMondstadt());
        WhisperingWoods.getWhisperingWoods().setHeadRegion(Mondstadt.getMondstadt());

    }

    public Set<Region> getRegions() {
        return regions;
    }

    public Set<Region> getHeadsRegions() {
        return headsRegions;
    }

    public static LocationManager getInstance() {
        if (locationManager == null) {
            locationManager = new LocationManager();
        }
        return locationManager;
    }
}
