package org.glow.location.region.liyue;

import org.glow.location.Region;

public class Liyue extends Region {

    private static final String name = "Ли Юэ";
    private static final String image = "https://cdn.discordapp.com/attachments/882560343983915029/1064985220731912212/Emblem_Mondstadt.webp";
    private static final Liyue liyue = new Liyue(name, image);

    public Liyue(String name, String image) {
        super(name, image);
    }


    public static Liyue getLiyue() {
        return liyue;
    }
}
