package org.glow.timeaction;

import org.glow.filemanager.Save;
import org.glow.person.Player;

public class TimeAction implements Runnable {

    private static final TimeAction timeAction = new TimeAction();

    private TimeAction() {}

    @Override
    public void run() {

        for (Player player : Player.getPlayerList()) {

            if (player.getEnergy() < 5) {
                player.setEnergy(player.getEnergy() + 1);
            }

            if (player.getHealth() < player.getMaxHealth()) {
                player.setHealth(player.getHealth() + 10);
                if (player.getHealth() > player.getMaxHealth()) {
                    player.setHealth(player.getMaxHealth());
                }
            }

            if (player.getMana() < player.getMaxMana()) {
                player.setMana(player.getMana() + 10);
                if (player.getMana() > player.getMaxMana()) {
                    player.setMana(player.getMaxMana());
                }
            }

            Save.getSave().saveFile(player);
        }

    }

    public static TimeAction getTimeAction() {
        return timeAction;
    }
}
