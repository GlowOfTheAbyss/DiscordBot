package org.glow.activities.battle;

import discord4j.core.object.entity.Message;
import org.glow.fileManager.Save;
import org.glow.message.Characteristic;
import org.glow.message.MessageSender;
import org.glow.message.Parameters;
import org.glow.message.TextManager;
import org.glow.person.Person;
import org.glow.person.PersonManager;
import org.glow.person.Player;
import org.glow.storage.InventoryManager;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Battle {

    private Person attacker;
    private Person defender;
    private final Message message;
    private final PersonManager personManager;

    public Battle(Message message, Person attacker, Person defender) {
        this.attacker = attacker;
        this.defender = defender;
        this.message = message;
        personManager = PersonManager.getInstance();
    }

    public void start() {

        while (true) {

            attack();
            if (attacker.getHealth() <= 0) {

                win(defender, attacker);
                break;

            } else if (defender.getHealth() <= 0) {

                win(attacker, defender);
                break;

            } else {

                Person temp = attacker;
                attacker = defender;
                defender = temp;

            }

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException exception) {
                MessageSender.getInstance().sendMessageInChannel(message, "Ошбка во время простоя");
            }

        }

    }

    private void attack() {

        int attackerAttack = new Random().nextInt(21)
                + (2 * attacker.getPerception())
                + ((int) (0.5 * attacker.getLuck()));

        int defenderDefend = new Random().nextInt(21)
                + (2 * attacker.getAgility())
                + ((int) (0.5 * attacker.getLuck()));

        if (attackerAttack > defenderDefend) {

            if (attackerAttack > defenderDefend * 2) {
                superHit();
            } else {
                hit();
            }

        } else {

            if (defenderDefend > attackerAttack * 2) {
                parrying();
            } else {
                dodge();
            }

        }

    }

    private void hit() {

        int attack = new Random().nextInt(11)
                + (2 * attacker.getStrength())
                + ((int) (0.5 * attacker.getLuck()))
                + InventoryManager.getInstance().getAttack(attacker.getInventory());

        int defend = new Random().nextInt(7)
                + InventoryManager.getInstance().getArmor(defender.getInventory());

        int damage = attack - defend;
        if (damage <= 0) {
            damage = 1;
        }

        defender.setHealth(defender.getHealth() - damage);
        if (defender instanceof Player) {
            Save.getSave().saveFile((Player) defender);
        }

        String title = personManager.getPersonName(attacker) + " попадает по " + personManager.getPersonName(defender);
        String description = """
                И наносит %s урона
                
                %s
                """;

        description = String.format(description, damage,
                TextManager.getInstance().getBattleParameters(attacker, defender));

        MessageSender.getInstance().sendMessageInChannel(message, title, description);

    }

    private void superHit() {

        int attack = new Random().nextInt(11)
                + (4 * attacker.getStrength())
                + attacker.getLuck()
                + InventoryManager.getInstance().getAttack(attacker.getInventory());

        int defend = new Random().nextInt(7)
                + InventoryManager.getInstance().getArmor(defender.getInventory());

        int damage = attack - defend;
        if (damage <= 0) {
            damage = 2;
        }

        defender.setHealth(defender.getHealth() - damage);
        if (defender instanceof Player) {
            Save.getSave().saveFile((Player) defender);
        }

        String title = personManager.getPersonName(attacker) + " критический удар по " + personManager.getPersonName(defender);
        String description = """
                И наносит %s урона
                
                %s
                """;

        description = String.format(description, damage,
                TextManager.getInstance().getBattleParameters(attacker, defender));
        MessageSender.getInstance().sendMessageInChannel(message, title, description);

    }

    private void parrying() {

        int attack = new Random().nextInt(11)
                + (2 * defender.getStrength())
                + ((int) (0.5 * defender.getLuck()))
                + InventoryManager.getInstance().getAttack(defender.getInventory());

        int defend = new Random().nextInt(7)
                + InventoryManager.getInstance().getArmor(attacker.getInventory());

        int damage = attack - defend;
        if (damage <= 0) {
            damage = 1;
        }

        attacker.setHealth(attacker.getHealth() - damage);
        if (attacker instanceof Player) {
            Save.getSave().saveFile((Player) attacker);
        }

        String title = personManager.getPersonName(defender) + " парирует атаку " + personManager.getPersonName(attacker);
        String description = """
                И отражает %s урона
                
                %s
                """;

        description = String.format(description, damage,
                TextManager.getInstance().getBattleParameters(attacker, defender));
        MessageSender.getInstance().sendMessageInChannel(message, title, description);

    }

    private void dodge() {

        String title = personManager.getPersonName(defender) + " уворачивается от атаки " + personManager.getPersonName(attacker);
        MessageSender.getInstance().sendMessageInChannel(message, title, TextManager.getInstance().getBattleParameters(attacker, defender));

    }

    private void win(Person winner, Person loser) {

        BattleManager.getInstance().finishBattle(this);

        if (winner instanceof Player) {
            playerWin((Player) winner, loser);
        } else if (loser instanceof Player){
            npcWin(winner,(Player) loser);
        }

    }


    private void playerWin(Player player, Person npc) {

        int coins = 2 * personManager.getPersonCombatLevel(npc) * 10;
        player.setCoins(player.getCoins() + coins);
        Save.getSave().saveFile(player);

        String title = personManager.getPersonName(player) + " побеждает";
        String description = """
                %s находит у %s %s %s
                """;
        description = String.format(description, personManager.getPersonName(player),
                personManager.getPersonName(npc), coins, Parameters.COINS);

        MessageSender.getInstance().sendMessageInChannel(message, title, description);

    }

    private void npcWin(Person npc, Player player) {

        int lostCoins = 2 * PersonManager.getInstance().getPersonCombatLevel(npc) * 10;

        if (lostCoins > player.getCoins()) {
            trauma(npc, player);
        } else {

            String title = PersonManager.getInstance().getPersonName(npc)+ " побеждает " + PersonManager.getInstance().getPersonName(player);

            player.setCoins(player.getCoins() - lostCoins);
            player.setHealth(10);
            Save.getSave().saveFile(player);

            String description = """
                    %s теряет %s %s
                    
                    %s
                    """;
            description = String.format(description, PersonManager.getInstance().getPersonName(player), lostCoins, Parameters.COINS,
                    TextManager.getInstance().getPlayerParameters(player));

            MessageSender.getInstance().sendMessageInChannel(message, title, description);

        }

    }

    private void trauma(Person npc, Player player) {

        int randomSkill = new Random().nextInt(6);
        Characteristic characteristic = null;

        switch (randomSkill) {
            case 0 -> {
                player.setStrength(player.getStrength() - 1);
                characteristic = Characteristic.STRENGTH;
            }
            case 1 -> {
                player.setEndurance(player.getEndurance() - 1);
                characteristic = Characteristic.ENDURANCE;
            }
            case 2 -> {
                player.setAgility(player.getAgility() - 1);
                characteristic = Characteristic.AGILITY;
            }
            case 3 -> {
                player.setIntelligence(player.getIntelligence() - 1);
                characteristic = Characteristic.INTELLIGENCE;
            }
            case 4 -> {
                player.setPerception(player.getPerception() - 1);
                characteristic = Characteristic.PERCEPTION;
            }
            case 5 -> {
                player.setLuck(player.getLuck() - 1);
                characteristic = Characteristic.LUCK;
            }
        }

        Save.getSave().saveFile(player);

        String title = PersonManager.getInstance().getPersonName(npc)+ " побеждает " + PersonManager.getInstance().getPersonName(player);
        String description = """
                %s получает травму и его %s уменьшается на 1
                
                %s
                
                %s
                """;
        description = String.format(description,
                PersonManager.getInstance().getPersonName(player), characteristic.getName(),
                TextManager.getInstance().getPlayerCharacteristic(player),
                TextManager.getInstance().getPlayerParameters(player));

        MessageSender.getInstance().sendMessageInChannel(message, title, description);

    }

    public Set<Person> getParticipants() {

        return Set.of(attacker, defender);

    }

}
