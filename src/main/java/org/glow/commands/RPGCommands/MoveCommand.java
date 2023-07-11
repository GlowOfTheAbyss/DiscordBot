package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import org.glow.Main;
import org.glow.commands.Command;
import org.glow.fileManager.Save;
import org.glow.map.location.action.Action;
import org.glow.map.location.*;
import org.glow.map.regions.Region;
import org.glow.message.MessageSender;
import org.glow.person.PersonManager;
import org.glow.person.Player;

public class MoveCommand extends Command {

    private static MoveCommand moveCommand;

    private MoveCommand(){
        setName("move");
        String info = """
                команда для передвижения по миру
                %s%s - показывает текущее положение персонажа и список соседних локаций
                %s%s [название локации] - отправится в указанную локацию
                """;

        setInfo(String.format(info,
                Main.systems.commandPrefix, getName(),
                Main.systems.commandPrefix, getName()));
    }

    @Override
    public void start(Message message) {

        try {

            Player player = userToPlayer(message);

            if (playerInBattle(player)) {
                MessageSender.getInstance().sendMessageInChannel(message, "Находясь в битве нельзя это использовать");
                return;
            }

            if (lengthCheck(message, 1)) {
                showInfo(message, player);
            } else {
                move(message, player);
            }

        } catch (IllegalArgumentException exception) {
            MessageSender.getInstance().sendMessageInChannel(message, exception.getMessage());
        }

    }

    private void showInfo(Message message, Player player) {

        Region currentRegion = PersonManager.getInstance().getPlayerRegion(player);
        Region headRegion = currentRegion.getHeadRegion();

        if (headRegion == null) {
            descriptionGenerator(message, currentRegion);
        } else {
            descriptionGenerator(message, headRegion, currentRegion);
        }

    }

    private void descriptionGenerator(Message message, Region currentRegion) {

        String title = "Вы находитесь в " + currentRegion.getName();
        String image = currentRegion.getImage();

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("**Вы можете перейти в регион: **\n");
        for (Region headsRegion : LocationManager.getInstance().getHeadsRegions()) {
            stringBuilder.append(headsRegion.getName()).append("\n");
        }

        if (!(currentRegion.getRegions().isEmpty())) {

            stringBuilder.append("\n");
            stringBuilder.append("**Вы можете перейти во внутренние локации: **\n");
            for (Region region : currentRegion.getRegions()) {
                stringBuilder.append(region.getName()).append("\n");
            }

        }

        if (!(currentRegion.getActions().isEmpty())) {

            stringBuilder.append("\n");
            stringBuilder.append("**Вы можете провзоимодействовать с: **\n");
            for (Action action : currentRegion.getActions()) {
                stringBuilder.append(action.getName()).append(" ").append(action.getDescription()).append("\n");
            }

        }

        MessageSender.getInstance().sendMessageInChannel(message, title, stringBuilder.toString(), image);

    }

    private void descriptionGenerator(Message message, Region headRegion, Region currentRegion) {

        String title = "Вы находитесь в " + currentRegion.getName();
        String image = currentRegion.getImage();

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("**Вы можете вернуться в ")
                .append(headRegion.getName())
                .append("**\n");
        stringBuilder.append("\n");

        stringBuilder.append("**Вы можете перейти в соседние локации: **\n");
        for (Region region : headRegion.getRegions()) {
            stringBuilder.append(region.getName()).append("\n");
        }

        if (!(currentRegion.getRegions().isEmpty())) {

            stringBuilder.append("\n");
            stringBuilder.append("**Вы можете перейти во внутренние локации: **\n");
            for (Region region : currentRegion.getRegions()) {
                stringBuilder.append(region.getName()).append("\n");
            }

        }

        if (!(currentRegion.getActions().isEmpty())) {

            stringBuilder.append("\n");
            stringBuilder.append("**Вы можете провзоимодействовать с: **\n");
            for (Action action : currentRegion.getActions()) {
                stringBuilder.append(action.getName()).append(" ").append(action.getDescription()).append("\n");
            }

        }

        MessageSender.getInstance().sendMessageInChannel(message, title, stringBuilder.toString(), image);

    }

    private void move(Message message, Player player) {

        String thisLocationName = message.getContent().replaceFirst(Main.systems.commandPrefix + getName() + " ", "");

        for (Location location : LocationManager.getInstance().getRegions()) {
            if (location.getName().equalsIgnoreCase(thisLocationName)) {

                PersonManager.getInstance().setPlayerLocation(player, location);
                Save.getSave().saveFile(player);
                showInfo(message, player);
                return;

            }
        }

        throw new IllegalArgumentException("Локация " + thisLocationName + " не найдена");

    }

    public static MoveCommand getMoveCommand() {
        if (moveCommand == null) {
            moveCommand = new MoveCommand();
        }
        return moveCommand;
    }

}
