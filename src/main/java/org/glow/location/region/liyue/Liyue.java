package org.glow.location.region.liyue;

import org.glow.location.Region;

public class Liyue extends Region {
    private static Liyue liyue;

    private Liyue() {
        setName("Ли Юэ");
        setImage("https://cdn.discordapp.com/attachments/1066672288897978439/1086592143709184040/Liyue.webp");
    }

    public static Liyue getLiyue() {
        if (liyue == null) {
            liyue = new Liyue();
        }
        return liyue;
    }

}
