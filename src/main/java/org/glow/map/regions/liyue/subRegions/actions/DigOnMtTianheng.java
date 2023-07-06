package org.glow.map.regions.liyue.subRegions.actions;

import discord4j.core.object.entity.Message;
import org.glow.Main;
import org.glow.actions.chests.CommonChest;
import org.glow.actions.chests.ExquisiteChest;
import org.glow.commands.RPGCommands.MineCommand;
import org.glow.fileManager.Save;
import org.glow.map.location.action.Action;
import org.glow.message.Parameters;
import org.glow.message.TextManager;
import org.glow.person.PersonManager;
import org.glow.person.Player;

import java.util.Random;

public class DigOnMtTianheng extends Action {

    public DigOnMtTianheng(Message message, Player player) {
        super(message, player);
        setName(Main.systems.commandPrefix + MineCommand.getMineCommand().getName());
        setDesctiption("Отправиться в заброшенную шахту");
    }

    @Override
    public void startAction() {

        int random = new Random().nextInt(101);
        int chestChance = 3;

        if (chestChance >= random) {
            chest();
        } else {
            deposit();
        }

    }

    private void chest() {

        int random = new Random().nextInt(101);
        int exquisiteChestChance = 5;

        if (exquisiteChestChance >= random) {
            new ExquisiteChest(getMessage(), getPlayer()).openChest();
        } else {
            new CommonChest(getMessage(), getPlayer()).openChest();
        }

    }

    private void deposit() {

        int random = new Random().nextInt(101);
        int whiteIronDepositChance = 10;

        if (whiteIronDepositChance >= random) {
            whiteIronDeposit();
        } else {
            ironDeposit();
        }

    }

    private void ironDeposit() {

        String title = PersonManager.getInstance().getPersonName(getPlayer()) + " находит залежь железа";
        int extraction = new Random().nextInt(1, 4) + getPlayer().getStrength() + getPlayer().getEndurance();
        extraction *= 10;

        getPlayer().setCoins(getPlayer().getCoins() + extraction);
        Save.getSave().saveFile(getPlayer());

        createMessage(title, extraction);

    }

    private void whiteIronDeposit() {

        String title = PersonManager.getInstance().getPersonName(getPlayer()) + " находит залежь белого железа";
        int extraction = new Random().nextInt(2, 7) + getPlayer().getStrength() + (2 * getPlayer().getEndurance());
        extraction *= 10;

        getPlayer().setCoins(getPlayer().getCoins() + extraction);
        Save.getSave().saveFile(getPlayer());

        createMessage(title, extraction);

    }

    private void createMessage(String title, int coins) {

        String description = """
                %s получает %s %s
                
                %s
                """;

        sendMessageInChannel(title, String.format(description,
                PersonManager.getInstance().getPersonName(getPlayer()), coins, Parameters.COINS.getName().replaceFirst("а", "ы"),
                TextManager.getInstance().getPlayerParameters(getPlayer())));
    }

}
