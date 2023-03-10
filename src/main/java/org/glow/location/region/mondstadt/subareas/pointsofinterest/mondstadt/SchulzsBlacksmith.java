package org.glow.location.region.mondstadt.subareas.pointsofinterest.mondstadt;

import org.glow.location.*;

public class SchulzsBlacksmith extends PointsOfInterest {

    private static final String name = "Кузница Шульца";
    private static final String image = "https://cdn.discordapp.com/attachments/882560343983915029/1065685456643162213/SchulzsBlacksmith.png";
    private static final SchulzsBlacksmith schulzsBlacksmith = new SchulzsBlacksmith(name, image);

    public SchulzsBlacksmith(String name, String image) {
        super(name, image);
    }

    public static SchulzsBlacksmith getSchulzsBlacksmith() {
        return schulzsBlacksmith;
    }

}
