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
