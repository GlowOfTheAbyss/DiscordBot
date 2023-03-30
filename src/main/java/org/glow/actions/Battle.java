package org.glow.actions;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.fileManager.Save;
import org.glow.item.Item;
import org.glow.person.Person;
import org.glow.person.Player;

import java.util.List;
import java.util.Random;

public class Battle {

    private Person attacker;
    private Person defender;
    private final Message message;

    public Battle(Message message, Person attacker, Person defender) {
        this.attacker = attacker;
        this.defender = defender;
        this.message = message;
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

        }

    }

    private void attack() {

        int attackerAttack = new Random().nextInt(21) + (2 * attacker.getPerception()) + ((int) (0.5 * attacker.getLuck()));
        int defenderDefend = new Random().nextInt(21) + (2 * attacker.getAgility()) + ((int) (0.5 * attacker.getLuck()));

        if (attackerAttack > defenderDefend) {

            if (attackerAttack > defenderDefend * 2) {
                superHit();
            }
            hit();

        } else {

            if (defenderDefend > attackerAttack * 2) {
                parrying();
            }
            dodge();

        }

    }

    private void hit() {

        int attack = new Random().nextInt(11) + (2 * attacker.getStrength())
                + ((int) (0.5 * attacker.getLuck())) * attacker.getInventory().getAttack();

        int defend = new Random().nextInt(7) + defender.getInventory().getArmor();

        int damage = attack - defend;
        defender.setHealth(defender.getHealth() - damage);
        if (defender instanceof Player) {
            Save.getSave().saveFile((Player) defender);
        }

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        builder.title(attacker.getName() + " попадает по " + defender.getName());
        builder.description("И наносит " + damage + " урона\n\n"
                + attacker.getName() + "HP : " + attacker.getHealth() + "\n"
                + defender.getName() + "HP : " + defender.getHealth());

        message.getChannel().block().createMessage(builder.build()).block();

    }

    private void superHit() {

        int attack = new Random().nextInt(11) + (4 * attacker.getStrength())
                + attacker.getLuck() * attacker.getInventory().getAttack();

        int defend = new Random().nextInt(7) + defender.getInventory().getArmor();

        int damage = attack - defend;
        defender.setHealth(defender.getHealth() - damage);
        if (defender instanceof Player) {
            Save.getSave().saveFile((Player) defender);
        }

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        builder.title(attacker.getName() + " попадает по " + defender.getName());
        builder.description("И наносит критический удар : " + damage + " урона\n\n"
                + attacker.getName() + "HP : " + attacker.getHealth() + "\n"
                + defender.getName() + "HP : " + defender.getHealth());

        message.getChannel().block().createMessage(builder.build()).block();

    }

    private void parrying() {

        int attack = new Random().nextInt(11) + (2 * defender.getStrength())
                + ((int) (0.5 * defender.getLuck())) * defender.getInventory().getAttack();

        int defend = new Random().nextInt(7) + attacker.getInventory().getArmor();

        int damage = attack - defend;

        attacker.setHealth(attacker.getHealth() - damage);
        if (attacker instanceof Player) {
            Save.getSave().saveFile((Player) attacker);
        }

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        builder.title(defender.getName() + " парирует атаку " + attacker.getName());
        builder.description("И наносит " + damage + " урона\n\n"
                + attacker.getName() + "HP : " + attacker.getHealth() + "\n"
                + defender.getName() + "HP : " + defender.getHealth());

        message.getChannel().block().createMessage(builder.build()).block();

    }

    private void dodge() {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        builder.title(defender.getName() + " уворачивается от атаки " + attacker.getName());
        builder.description(attacker.getName() + "HP : " + attacker.getHealth() + "\n"
                + defender.getName() + "HP : " + defender.getHealth());

        message.getChannel().block().createMessage(builder.build()).block();

    }

    private void win(Person winner, Person loser) {

        if (winner instanceof Player) {
            playerWin((Player) winner, loser);
        } else if (loser instanceof Player){
            npcWin(winner,(Player) loser);
        }

    }


    private void playerWin(Player player, Person npc) {

        int random = new Random().nextInt(101);
        int lootChance = 1 + ((int) (0.5 * player.getLuck()));
        if (lootChance > 5) {
            lootChance = 5;
        }

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title(player.getName() + " побеждает");
        message.getChannel().block().createMessage(builder.build()).block();
        builder.title("");

        if (lootChance > random) {
            loot(player, npc);
        }

        int coins = 2 * npc.getCombatLevel() * 10;
        player.setCoins(player.getCoins() + coins);
        Save.getSave().saveFile(player);

        builder.description(player.getName() + " находит у " + npc.getName() + " " + coins + " :pig2:");

        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    private void loot(Player player, Person npc) {

        if (npc.getInventory().getEquippedItems().isEmpty()) {
            return;
        }

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        Item randomItem = npc.getInventory().getEquippedItems().get(new Random().nextInt(npc.getInventory().getEquippedItems().size()));

        if (player.getInventory().getBag().size() >= player.getInventory().getBagSize()) {
            builder.title("Вы нашли предмет " + randomItem.getName());
            builder.description("Но у ва нет места в инвентаре что бы его забрать");

            message.getChannel().block().createMessage(builder.build()).block();
            return;
        }

        player.getInventory().getBag().add(randomItem);
        Save.getSave().saveFile(player);

        builder.description("Вы нашли предмет " + randomItem.getName() + "\n\n"
                + ":pig2: " + player.getCoins() + "\n"
                + "Энергия: " + player.getEnergy() + "\n"
                + "Здоровье: " + player.getHealth() + "\n"
                + "Мана: " + player.getMana() + "\n");

        message.getChannel().block().createMessage(builder.build()).block();

    }

    private void npcWin(Person npc, Player player) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        builder.title(npc.getName() + " побеждает " + player.getName());
        message.getChannel().block().createMessage(builder.build()).block();
        builder.title("");

        int traumaChance = 5;
        int itemChance = 10;

        int lostCoins = 2 * npc.getCombatLevel() * 10;

        int random = new Random().nextInt(101);

        if (traumaChance > random) {
            trauma(player);
        } else if (itemChance > random) {
            itemLost(player);
        }

        if (lostCoins > player.getCoins()) {
            trauma(player);
        } else {

            player.setCoins(player.getCoins() - lostCoins);
            player.setHealth(10);
            Save.getSave().saveFile(player);

            builder.description(player.getName() + " теряет " + lostCoins + " :pig2:\n\n"
                    + ":pig2: " + player.getCoins() + "\n"
                    + "Энергия: " + player.getEnergy() + "\n"
                    + "Здоровье: " + player.getHealth() + "\n"
                    + "Мана: " + player.getMana() + "\n");

            message.getChannel().block().createMessage(builder.build()).block();
            message.delete().block();

        }

    }

    private void trauma(Player player) {

        int randomSkill = new Random().nextInt(6);
        String skillName = "";

        switch (randomSkill) {
            case 0 -> {
                player.setStrength(player.getStrength() - 1);
                skillName = "Сила";
            }
            case 1 -> {
                player.setEndurance(player.getEndurance() - 1);
                skillName = "Выносливость";
            }
            case 2 -> {
                player.setAgility(player.getAgility() - 1);
                skillName = "Ловкость";
            }
            case 3 -> {
                player.setIntelligence(player.getIntelligence() - 1);
                skillName = "Интеллект";
            }
            case 4 -> {
                player.setPerception(player.getPerception() - 1);
                skillName = "Внимание";
            }
            case 5 -> {
                player.setLuck(player.getLuck() - 1);
                skillName = "Удача";
            }
        }

        Save.getSave().saveFile(player);

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.description(player.getName() + " получает травму\n"
                + skillName + " уменьшено на 1\n\n"
                + ":pig2: " + player.getCoins() + "\n"
                + "Энергия: " + player.getEnergy() + "\n"
                + "Здоровье: " + player.getHealth() + "\n"
                + "Мана: " + player.getMana() + "\n");

        message.getChannel().block().createMessage(builder.build()).block();

    }

    private void itemLost(Player player) {

        List<Item> items;

        if (player.getInventory().getBag().isEmpty()) {
            return;
        } else {
            items = player.getInventory().getBag();
        }

        Item randomItem = items.get(new Random().nextInt(items.size()));

        player.getInventory().getBag().remove(randomItem);
        Save.getSave().saveFile(player);

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.description(player.getName() + " теряет предмет\n"
                + randomItem.getName() + " потерян\n\n"
                + ":pig2: " + player.getCoins() + "\n"
                + "Энергия: " + player.getEnergy() + "\n"
                + "Здоровье: " + player.getHealth() + "\n"
                + "Мана: " + player.getMana() + "\n");

        message.getChannel().block().createMessage(builder.build()).block();

    }

}
