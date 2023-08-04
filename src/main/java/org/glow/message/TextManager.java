package org.glow.message;

import org.glow.item.Item;
import org.glow.magic.Spell;
import org.glow.person.Person;
import org.glow.person.PersonManager;
import org.glow.person.Player;
import org.glow.storage.InventoryManager;

public class TextManager {

    private static TextManager textManager;

    private TextManager() {}

    public String getPlayerParameters(Player player) {

        String playerParameters = """
                %s: %s
                %s: %s
                %s: %s
                %s: %s
                """;

        return String.format(playerParameters,
                Parameters.COINS.getName(), player.getCoins(),
                Parameters.ENERGY.getName(), player.getEnergy(),
                Parameters.HEALTH.getName(), player.getHealth(),
                Parameters.MANA.getName(), player.getMana());

    }

    public String getPlayerCharacteristic(Player player) {

        String playerCharacteristic = """
                %s: %s
                %s: %s
                %s: %s
                %s: %s
                %s: %s
                %s: %s
                """;

        return String.format(playerCharacteristic,
                Characteristic.STRENGTH.getName(), player.getStrength(),
                Characteristic.ENDURANCE.getName(), player.getEndurance(),
                Characteristic.AGILITY.getName(), player.getAgility(),
                Characteristic.INTELLIGENCE.getName(), player.getIntelligence(),
                Characteristic.PERCEPTION.getName(), player.getPerception(),
                Characteristic.LUCK.getName(), player.getLuck());
    }

    public String getPlayerEquippedItems(Player player) {

        String playerEquippedItems = """
                %s: %s
                %s: %s
                
                %s: %s
                %s: %s
                %s: %s
                %s: %s
                %s: %s
                
                %s: %s
                %s: %s
                """;

        return String.format(playerEquippedItems,
                Equipment.DEFEND.getName(), InventoryManager.getInstance().getArmor(player.getInventory()),
                Equipment.ATTACK.getName(), InventoryManager.getInstance().getAttack(player.getInventory()),

                Equipment.HEAD.getName(), player.getInventory().getHead().getName(),
                Equipment.BODY.getName(), player.getInventory().getBody().getName(),
                Equipment.LEGS.getName(), player.getInventory().getLegs().getName(),
                Equipment.RIGHT_HAND.getName(), player.getInventory().getRightHand().getName(),
                Equipment.LEFT_HAND.getName(), player.getInventory().getLeftHand().getName(),

                Equipment.NECK.getName(), player.getInventory().getNeck().getName(),
                Equipment.FINGER.getName(), player.getInventory().getFinger().getName()
                );

    }

    public String getPlayerInventory(Player player) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Сумка: ");

        if (player.getInventory().getBag().isEmpty()) {
            stringBuilder.append("Пусто");
        } else {
            for (Item items : player.getInventory().getBag()) {
                stringBuilder.append(items.getName())
                        .append(" ");
            }
        }

        return stringBuilder.toString();

    }

    public String getPlayerSkillBook(Player player) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Книга заклинаний: ")
                .append("\n");

        if (player.getSpellBook().getListSpell().isEmpty()) {
            stringBuilder.append("Пусто");
        } else {
            for (Spell spell : player.getSpellBook().getListSpell()) {

                stringBuilder.append(spell.getName())
                        .append(" | ")
                        .append(spell.getCoastInMana())
                        .append(" мана")
                        .append("\n");

            }
        }

        return stringBuilder.toString();

    }

    public String getBattleParameters(Person attacker, Person defender) {

        String description = """
                %s %s: %s
                %s %s: %s
                """;

        return String.format(description,
                PersonManager.getInstance().getPersonName(attacker), Parameters.HEALTH, attacker.getHealth(),
                PersonManager.getInstance().getPersonName(defender), Parameters.HEALTH, defender.getHealth());

    }

    public static TextManager getInstance() {
        if (textManager == null) {
            textManager = new TextManager();
        }
        return textManager;
    }
}
