package org.glow.timeaction;

import org.glow.fileManager.Save;
import org.glow.person.PersonManager;
import org.glow.person.Player;

public class TimeAction implements Runnable {

    private static final TimeAction timeAction = new TimeAction();

    private TimeAction() {}

    @Override
    public void run() {

        for (Player player : PersonManager.getInstance().getPlayers()) {

            if (player.getEnergy() < 5) {
                player.setEnergy(player.getEnergy() + 1);
            }

            if (player.getHealth() < PersonManager.getInstance().getPlayerMaxHealth(player)) {
                player.setHealth(player.getHealth() + 10);
                if (player.getHealth() > PersonManager.getInstance().getPlayerMaxHealth(player)) {
                    player.setHealth(PersonManager.getInstance().getPlayerMaxHealth(player));
                }
            }

            if (player.getMana() < PersonManager.getInstance().getplayerMaxMana(player)) {
                player.setMana(player.getMana() + 10);
                if (player.getMana() > PersonManager.getInstance().getplayerMaxMana(player)) {
                    player.setMana(PersonManager.getInstance().getplayerMaxMana(player));
                }
            }

            Save.getSave().saveFile(player);
        }

    }

    public static TimeAction getTimeAction() {
        return timeAction;
    }
}
