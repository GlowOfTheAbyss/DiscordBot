package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import org.glow.commands.Command;
import org.glow.fileManager.Save;
import org.glow.location.region.liyue.subareas.MtTianheng;
import org.glow.location.region.liyue.subareas.actions.DigOnMtTianheng;
import org.glow.person.Player;

public class MineCommand extends Command {


    private static MineCommand mineCommand;

    private MineCommand() {
        setName("mine");
        setInfo("отправиться в шахту в специальных местах");
    }

    @Override
    public void start(Message message) {

        Player player = userToPlayer(message);
        if (player == null) {
            return;
        }

        if (player.getLocation() instanceof MtTianheng) {

            player.setEnergy(player.getEnergy() - 1);
            Save.getSave().saveFile(player);

            DigOnMtTianheng.getDigOnMtTianheng().startAction(message, player);
        }

    }

    public static MineCommand getMineCommand() {
        if (mineCommand == null) {
            mineCommand = new MineCommand();
        }
        return mineCommand;
    }
}