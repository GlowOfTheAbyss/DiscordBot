package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.commands.Command;
import org.glow.fileManager.Save;
import org.glow.person.Player;

import java.util.List;

public class LevelUpCommand extends Command {

    private static LevelUpCommand levelUpCommand;

    private final List<String> skillList = List.of("Сила", "Выносливость", "Ловкость", "Интеллект", "Внимание", "Удача");
    private int oneLevelCost = 600;
    private int perLevelCost = 200;

    private LevelUpCommand() {
        setName("level_up");
        setInfo("""
                комманда для прокачки уровня персонажа
                !level_up [характеристика которую нужно прокачать]
                """);
    }

    @Override
    public void start(Message message) {

        Player player = userToPlayer(message);
        if (player == null) {
            return;
        }

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        if (playerInBattle(player, message)) {
            return;
        }

        if (lengthCheck(message, 2)) {

            levelUp(message, player);

        } else {

            builder.title("Ошибка");
            builder.description("Для команды !" + getName() + " требуется указать характеристику для прокачки" + "\n"
                    + "Пример: !" + getName() + " Сила");
            message.getChannel().block().createMessage(builder.build()).block();
            message.delete().block();

        }

    }

    public void levelUp(Message message, Player player) {

        String thisSkill = message.getContent().replaceFirst("!" + getName() + " ", "");
        int skillLevel = getSkillLevel(player, thisSkill);
        if (skillLevel == -1) {
            EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
            builder.title(thisSkill + " не найден");
            message.getChannel().block().createMessage(builder.build()).block();
            message.delete().block();
            return;
        }

        int skillCost = oneLevelCost + (perLevelCost * skillLevel);

        if (player.getCoins() < skillCost) {
            EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

            builder.title("Недостаточно :pig2: для прокачки: " + thisSkill);
            builder.description("Требуется: " + skillCost + "\n\n"

                    + ":pig2:" + player.getCoins() + "\n"
                    + "Энергия: " + player.getEnergy() + "\n"
                    + "Здоровье: " + player.getHealth() + "\n"
                    + "Мана: " + player.getMana() + "\n");
            message.getChannel().block().createMessage(builder.build()).block();
            message.delete().block();
            return;
        }

        player.setCoins(player.getCoins() - skillCost);
        setSkillLevel(player, thisSkill, 1);
        Save.getSave().saveFile(player);

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title(thisSkill + " + 1 ");
        builder.description("Сила: " + player.getStrength() + "\n"
                + "Выносливость: " + player.getEndurance() + "\n"
                + "Ловкость: " + player.getAgility() + "\n"
                + "Интеллект: " + player.getIntelligence() + "\n"
                + "Внимание: " + player.getPerception() + "\n"
                + "Удача: " + player.getLuck() + "\n\n"

                + ":pig2:" + player.getCoins() + "\n"
                + "Энергия: " + player.getEnergy() + "\n"
                + "Здоровье: " + player.getHealth() + "\n"
                + "Мана: " + player.getMana() + "\n");
        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    private int getSkillLevel(Player player, String thisSkill) {

        for (String skill : skillList) {
            if (skill.equalsIgnoreCase(thisSkill)) {
                int index = skillList.indexOf(skill);

                return switch (index) {
                    case 0 -> player.getStrength();
                    case 1 -> player.getEndurance();
                    case 2 -> player.getAgility();
                    case 3 -> player.getIntelligence();
                    case 4 -> player.getPerception();
                    case 5 -> player.getLuck();
                    default -> -1;
                };
            }
        }

        throw new IllegalArgumentException("Skill not found");
    }

    private void setSkillLevel(Player player, String thisSkill, int increase) {

        for (String skill : skillList) {
            if (skill.equalsIgnoreCase(thisSkill)) {
                int index = skillList.indexOf(skill);

                switch (index) {
                    case 0 -> player.setStrength(player.getStrength() + increase);
                    case 1 -> player.setEndurance(player.getEndurance() + increase);
                    case 2 -> player.setAgility(player.getAgility() + increase);
                    case 3 -> player.setIntelligence(player.getIntelligence() + increase);
                    case 4 -> player.setPerception(player.getPerception() + increase);
                    case 5 -> player.setLuck(player.getLuck() + increase);
                }
            }
        }
    }

    public static LevelUpCommand getLevelUpCommand() {
        if (levelUpCommand == null) {
            levelUpCommand = new LevelUpCommand();
        }
        return levelUpCommand;
    }

}
