package org.glow.map.regions.liyue.subRegions.actions;

import discord4j.core.object.entity.Message;
import org.glow.Main;
import org.glow.activities.mine.LightMine;
import org.glow.commands.RPGCommands.MineCommand;
import org.glow.fileManager.Save;
import org.glow.map.location.action.Action;
import org.glow.person.Player;

public class DigOnMtTianheng extends Action {

    public DigOnMtTianheng(Message message, Player player) {
        super(message, player);
        setName(Main.getSystems().getCommandPrefix() + MineCommand.getMineCommand().getName());
        setDescription("Отправиться в заброшенную шахту");
    }

    @Override
    public void startAction() {

        getPlayer().setEnergy(getPlayer().getEnergy() - 1);
        Save.getSave().saveFile(getPlayer());

        new LightMine(getMessage(), getPlayer()).mine();

    }

}
