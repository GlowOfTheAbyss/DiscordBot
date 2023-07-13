package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import org.glow.Main;
import org.glow.commands.Command;
import org.glow.fileManager.Save;
import org.glow.message.Characteristic;
import org.glow.message.MessageSender;
import org.glow.message.Parameters;
import org.glow.message.TextManager;
import org.glow.person.Player;

import java.util.ArrayList;
import java.util.List;

public class LevelUpCommand extends Command {

    private static LevelUpCommand levelUpCommand;

    private final List<Characteristic> characteristics = new ArrayList<>();

    private LevelUpCommand() {
        setName("level_up");
        String info = """
                комманда для прокачки уровня персонажа
                %s%s [характеристика которую нужно прокачать]
                """;
        setInfo(String.format(info,
                Main.getSystems().getCommandPrefix(), getName()));

        characteristics.addAll(List.of(Characteristic.STRENGTH,
                Characteristic.ENDURANCE,
                Characteristic.AGILITY,
                Characteristic.INTELLIGENCE,
                Characteristic.PERCEPTION,
                Characteristic.LUCK));
    }

    @Override
    public void start(Message message) {

        try {

            Player player = userToPlayer(message);

            if (playerInBattle(player)) {
                MessageSender.getInstance().sendMessageInChannel(message, "Находясь в битве нельзя это использовать");
                return;
            }

            if (lengthCheck(message, 2)) {
                levelUp(message, player);
            } else {
                errorMassageGenerator(message);
            }

        } catch (IllegalArgumentException exception) {
            MessageSender.getInstance().sendMessageInChannel(message, exception.getMessage());
        }

    }

    private void errorMassageGenerator(Message message) {

        String title = "Ошибка";
        String description = """
                        Для команды %s%s требуется указать характеристику для прокачки
                        Пример: %s%s %s
                        """;
        description = String.format(description,
                Main.getSystems().getCommandPrefix(), getName(),
                Main.getSystems().getCommandPrefix(), getName(), Characteristic.STRENGTH.getName());

        MessageSender.getInstance().sendMessageInChannel(message, title, description);

    }

    public void levelUp(Message message, Player player) {

        String thisSkill = message.getContent().replaceFirst(Main.getSystems().getCommandPrefix() + getName() + " ", "");

        try {

            Characteristic characteristic = findCharacteristic(thisSkill);
            int price = getCharacteristicPrice(player, characteristic);

            if (player.getCoins() > price) {

                upCharacteristicLevel(player, characteristic);
                player.setCoins(player.getCoins() - price);
                Save.getSave().saveFile(player);

                successfulMessageGenerator(message, player, characteristic);

            } else {
                unsuccessfulMessageGenerator(message, player, characteristic, price);
            }

        } catch (IllegalArgumentException exception) {
            MessageSender.getInstance().sendMessageInChannel(message, exception.getMessage());
        }

    }

    private Characteristic findCharacteristic(String name) {

        for (Characteristic characteristic : characteristics) {
            if (characteristic.getName().equals(name)) {
                return characteristic;
            }
        }

        throw new IllegalArgumentException("Характеристика " + name + " не найдена");
    }

    private void successfulMessageGenerator(Message message, Player player, Characteristic characteristic) {

        String title = characteristic.getName() + " + 1";
        String description = """
                %s
                
                %s
                """;
        description = String.format(description,
                TextManager.getInstance().getPlayerCharacteristic(player),
                TextManager.getInstance().getPlayerParameters(player));

        MessageSender.getInstance().sendMessageInChannel(message, title, description);
    }

    private void unsuccessfulMessageGenerator(Message message, Player player, Characteristic characteristic, int price) {
        String title = "Не получилось прокачать характеристику";
        String description = """
                        Недостаточно %s для прокачки %s
                        Требуется: %s %s
                        
                        %s
                        """;
        description = String.format(description,
                Parameters.COINS.getName(), characteristic.getName(),
                price, Parameters.COINS.getName(),
                TextManager.getInstance().getPlayerParameters(player));
        MessageSender.getInstance().sendMessageInChannel(message, title, description);
    }

    private int getCharacteristicPrice(Player player, Characteristic characteristic) {

        int level = switch (characteristic) {
            case STRENGTH -> player.getStrength();
            case ENDURANCE -> player.getEndurance();
            case AGILITY -> player.getAgility();
            case INTELLIGENCE -> player.getIntelligence();
            case PERCEPTION -> player.getPerception();
            case LUCK -> player.getLuck();
        };

        int oneLevelCost = 600;
        int perLevelCost = 200;
        return oneLevelCost + (perLevelCost * level);

    }

    private void upCharacteristicLevel(Player player, Characteristic characteristic) {

        switch (characteristic) {
            case STRENGTH -> player.setStrength(player.getStrength() + 1);
            case ENDURANCE -> player.setEndurance(player.getEndurance() + 1);
            case AGILITY -> player.setAgility(player.getAgility() + 1);
            case INTELLIGENCE -> player.setIntelligence(player.getIntelligence() + 1);
            case PERCEPTION -> player.setPerception(player.getPerception() + 1);
            case LUCK -> player.setLuck(player.getLuck() + 1);
        }

    }

    public static LevelUpCommand getLevelUpCommand() {
        if (levelUpCommand == null) {
            levelUpCommand = new LevelUpCommand();
        }
        return levelUpCommand;
    }

}
