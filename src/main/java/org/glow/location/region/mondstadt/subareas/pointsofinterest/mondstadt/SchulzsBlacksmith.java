package org.glow.location.region.mondstadt.subareas.pointsofinterest.mondstadt;

import org.glow.location.*;

public class SchulzsBlacksmith extends PointsOfInterest {

    private static final SchulzsBlacksmith schulzsBlacksmith = new SchulzsBlacksmith();

    private SchulzsBlacksmith() {
        setName("Кузница Шульца");
        setImage("https://cdn.discordapp.com/attachments/882560343983915029/1065685456643162213/SchulzsBlacksmith.png");
    }

    public static SchulzsBlacksmith getSchulzsBlacksmith() {
        return schulzsBlacksmith;
    }

}
