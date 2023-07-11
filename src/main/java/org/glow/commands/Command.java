package org.glow.commands;

import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.Message;
import org.glow.activities.battle.Battle;
import org.glow.activities.battle.BattleManager;
import org.glow.person.PersonManager;
import org.glow.person.Player;

public abstract class Command implements LaunchedCommand {

    private String name;
    private String info;

    public static final Snowflake admin = Snowflake.of(238764551221280770L);

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    /*---*/
    protected Player userToPlayer(Message message) {
        for (Player player : PersonManager.getInstance().getPlayers()) {
            if (message.getAuthor().get().getId().equals(PersonManager.getInstance().getPlayerSnowflake(player))) {
                return player;
            }
        }

        throw new IllegalArgumentException("Игрок не найден");

    }

    protected boolean playerInBattle(Player player) {
        for (Battle battle : BattleManager.getInstance().getBattles()) {
            if (battle.getParticipants().contains(player)) {
                return true;
            }
        }
        return false;
    }

    protected boolean isNotAdmin(Player player) {
        return !PersonManager.getInstance().getPlayerSnowflake(player).equals(admin);
    }

    protected boolean lengthCheck(Message message, int length) {
        return message.getContent().split(" ").length == length;
    }

}
