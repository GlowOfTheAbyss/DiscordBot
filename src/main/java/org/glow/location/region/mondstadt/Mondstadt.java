package org.glow.location.region.mondstadt;

import org.glow.location.Region;

public class Mondstadt extends Region {

    private static final String name = "Мондштадт";
    private static final String image = "https://cdn.discordapp.com/attachments/882560343983915029/1064985220731912212/Emblem_Mondstadt.webp";

    private static final Mondstadt mondstadt = new Mondstadt(name, image);

    private Mondstadt(String name, String image) {
        super(name, image);
    }

    public static Mondstadt getMondstadt() {
        return mondstadt;
    }
}
