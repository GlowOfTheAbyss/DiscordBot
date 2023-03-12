package org.glow.location.region.mondstadt;

import org.glow.location.Region;

public class Mondstadt extends Region {

    private static final Mondstadt mondstadt = new Mondstadt();

    private Mondstadt() {
        setName("Мондштадт");
        setImage("https://cdn.discordapp.com/attachments/882560343983915029/1064985220731912212/Emblem_Mondstadt.webp");
    }

    public static Mondstadt getMondstadt() {
        return mondstadt;
    }

}
