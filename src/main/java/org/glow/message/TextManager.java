package org.glow.message;

import org.glow.person.Player;

public class TextManager {

    private static TextManager textManager;

    private TextManager() {}

    public String getPlayerParameters(Player player) {

        String standardDescription = """
                %s: %s
                %s: %s
                %s: %s
                %s: %s
                """;

        return String.format(standardDescription,
                Parameters.COINS.getName(), player.getCoins(),
                Parameters.ENERGY.getName(), player.getEnergy(),
                Parameters.HEALTH.getName(), player.getHealth(),
                Parameters.MANA.getName(), player.getMana());

    }

    public static TextManager getInstance() {
        if (textManager == null) {
            textManager = new TextManager();
        }
        return textManager;
    }
}
