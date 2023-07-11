package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import org.glow.commands.Command;
import org.glow.map.regions.liyue.subRegions.MtTianheng;
import org.glow.map.regions.liyue.subRegions.actions.DigOnMtTianheng;
import org.glow.message.MessageSender;
import org.glow.person.PersonManager;
import org.glow.person.Player;

public class MineCommand extends Command {


    private static MineCommand mineCommand;

    private MineCommand() {
        setName("mine");
        setInfo("""
                отправиться в шахту в специальных местах
                """);
    }

    @Override
    public void start(Message message) {

        try {

            Player player = userToPlayer(message);

            if (playerInBattle(player)) {
                MessageSender.getInstance().sendMessageInChannel(message, "Находясь в битве нельзя это использовать");
                return;
            }

            if (player.getEnergy() <= 0) {
                MessageSender.getInstance().sendMessageInChannel(message, "Не достаточно энергии");
                return;
            }

            if (PersonManager.getInstance().getPlayerRegion(player) instanceof MtTianheng) {
                new DigOnMtTianheng(message, player).startAction();
            }

        } catch (IllegalArgumentException exception) {
            MessageSender.getInstance().sendMessageInChannel(message, exception.getMessage());
        }

    }

    public static MineCommand getMineCommand() {
        if (mineCommand == null) {
            mineCommand = new MineCommand();
        }
        return mineCommand;
    }
}
