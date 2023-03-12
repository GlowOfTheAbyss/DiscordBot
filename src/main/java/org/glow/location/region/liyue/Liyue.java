package org.glow.location.region.liyue;

import org.glow.location.Region;

public class Liyue extends Region {
    private static final Liyue liyue = new Liyue();

    private Liyue() {
        setName("Ли Юэ");
        setImage("https://cdn.discordapp.com/attachments/882560343983915029/1064985220731912212/Emblem_Mondstadt.webp");
    }

    public static Liyue getLiyue() {
        return liyue;
    }

}
