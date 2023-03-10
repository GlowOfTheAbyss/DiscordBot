package org.glow.commands;

import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.commands.rpgcommands.*;
import org.glow.commands.systrmcommands.ShotDownCommand;
import org.glow.person.Player;

import java.util.ArrayList;
import java.util.List;

public class Command implements LaunchedCommand {

    private static final Command command = new Command();

    private static final List<Command> commandList = new ArrayList<>();
    static {
        commandList.add(BuyCommand.getBuyCommand());
        commandList.add(CastCommand.getCastCommand());
        commandList.add(EquipCommand.getEquipCommand());
        commandList.add(HealCommand.getHealCommand());
        commandList.add(InventoryCommand.getInventoryCommand());
        commandList.add(MoveCommand.getMoveCommand());
        commandList.add(StatsCommand.getStatsCommand());
        commandList.add(UnequipCommand.getUnequipCommand());
        commandList.add(UserToPlayerCommand.getUserToPlayerCommand());

        commandList.add(ShotDownCommand.getShotDownCommand());
    }

    protected String name;

    /*---*/
    protected static final Snowflake admin = Snowflake.of(238764551221280770L);

    protected Command() {
    }

    public static Command getCommand() {
        return command;
    }

    public List<Command> getCommandList() {
        return commandList;
    }

    public String getName() {
        return name;
    }

    @Override
    public void start(Message message) {}

    /*---*/
    protected Player userToPlayer(Message message) {
        for (Player player : Player.getPlayerList()) {
            if (message.getAuthor().get().getId().equals(player.getSnowflake())) {
                return player;
            }
        }
        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title("Игрок не найден");
        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();
        return null;
    }

    protected boolean isNotAdmin(Player player) {
        return !player.getSnowflake().equals(admin);
    }

    protected boolean lengthCheck(Message message, int lenght) {
        return message.getContent().split(" ").length == lenght;
    }

}
