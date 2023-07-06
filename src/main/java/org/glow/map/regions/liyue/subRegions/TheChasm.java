package org.glow.map.regions.liyue.subRegions;

import org.glow.map.regions.Region;

import java.util.Set;

public class TheChasm extends Region {

    private static TheChasm theChasm;

    private TheChasm() {
        setName("Разлом");
        setImage("https://cdn.discordapp.com/attachments/1066672288897978439/1086598614568161320/TheChasm.png");

        setRegions(Set.of());
        setActions(Set.of());

    }

    public static TheChasm getTheChasm() {
        if (theChasm == null) {
            theChasm = new TheChasm();
        }
        return theChasm;
    }

}
