package org.glow.actions.battle;

import discord4j.core.object.entity.Message;
import org.glow.person.Person;
import org.glow.person.Player;

import java.util.ArrayList;
import java.util.List;

public class BattleManager {

    private static BattleManager battleManager;

    private final List<Battle> battles = new ArrayList<>();

    private BattleManager() {}

    public void createBattle(Message message, Person player, Person npc) {

        Battle battle = new Battle(message, player, npc);
        battles.add(battle);

    }

    public void finishBattle(Battle battle) {
        battles.remove(battle);
    }

    public List<Battle> getBattles() {
        return battles;
    }

    public static BattleManager getInstance() {
        if (battleManager == null) {
            battleManager = new BattleManager();
        }
        return battleManager;
    }

}
