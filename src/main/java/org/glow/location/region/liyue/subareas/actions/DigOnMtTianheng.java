package org.glow.location.region.liyue.subareas.actions;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.actions.Chests;
import org.glow.fileManager.Save;
import org.glow.location.Action;
import org.glow.person.PersonManager;
import org.glow.person.Player;

import java.util.Random;

public class DigOnMtTianheng extends Action {

    private static DigOnMtTianheng digOnMtTianheng;

    public DigOnMtTianheng() {
        setName("Отправиться в заброшенную шахту | !mine");
    }

    @Override
    public void startAction(Message message, Player player) {

        int random = new Random().nextInt(101);
        int chestChance = 3;

        if (chestChance >= random) {
            chest(message, player);
        } else {
            deposit(message, player);
        }

    }

    private void chest(Message message, Player player) {

        player.setEnergy(player.getEnergy() + 1);
        Save.getSave().saveFile(player);

        int random = new Random().nextInt(101);
        int exquisiteChestChance = 5;

        if (exquisiteChestChance >= random) {
            Chests.getChests().getExquisiteChest(message, player);
        } else {
            Chests.getChests().getCommonChest(message, player);
        }

    }

    private void deposit(Message message, Player player) {

        int random = new Random().nextInt(101);
        int whiteIronDepositChance = 10;

        if (whiteIronDepositChance >= random) {
            whiteIronDeposit(message, player);
        } else {
            ironDeposit(message, player);
        }

    }

    private void ironDeposit(Message message, Player player) {

        String title = PersonManager.getInstance().getPersonName(player) + " находит залежь железа";
        int extraction = 1 + new Random().nextInt(3) + player.getStrength() + player.getEndurance();
        extraction *= 10;

        player.setCoins(player.getCoins() + extraction);
        Save.getSave().saveFile(player);

        sendMessage(message, player, title, extraction);

    }

    private void whiteIronDeposit(Message message, Player player) {

        String title = PersonManager.getInstance().getPersonName(player) + " находит залежь белого железа";
        int extraction = 2 + new Random().nextInt(4) + player.getStrength() + (2 * player.getEndurance());
        extraction *= 10;

        player.setCoins(player.getCoins() + extraction);
        Save.getSave().saveFile(player);

        sendMessage(message, player, title, extraction);

    }

    private void sendMessage(Message message, Player player, String title, int coins) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title(title);
        builder.description(PersonManager.getInstance().getPersonName(player) + " получает " + coins + " :pig2:\n\n"
                + ":pig2: " + player.getCoins() + "\n"
                + "Энергия: " + player.getEnergy() + "\n"
                + "Здоровье: " + player.getHealth() + "\n"
                + "Мана: " + player.getMana() + "\n");

        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();
    }

    public static DigOnMtTianheng getDigOnMtTianheng() {
        if (digOnMtTianheng == null) {
            digOnMtTianheng = new DigOnMtTianheng();
        }
        return digOnMtTianheng;
    }

}
