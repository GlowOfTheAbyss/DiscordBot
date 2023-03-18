package org.glow.location.region.liyue.subareas;

import org.glow.location.Subarea;

public class TheChasm extends Subarea {

    private static TheChasm theChasm;

    private TheChasm() {
        setName("Разлом");
        setImage("https://cdn.discordapp.com/attachments/1066672288897978439/1086598614568161320/TheChasm.png");
    }

    public static TheChasm getTheChasm() {
        if (theChasm == null) {
            theChasm = new TheChasm();
        }
        return theChasm;
    }

}
