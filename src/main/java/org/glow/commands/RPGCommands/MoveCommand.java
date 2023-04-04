package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.commands.Command;
import org.glow.fileManager.Save;
import org.glow.location.*;
import org.glow.person.Player;

import java.util.Set;

public class MoveCommand extends Command {

    private static MoveCommand moveCommand;

    private MoveCommand(){
        setName("move");
        setInfo("""
                команда для передвижения по миру
                !move - показывает текущее положение персонажа и список соседних локаций
                !move [название локации] - отправится в указанную локацию""");
    }

    @Override
    public void start(Message message) {

        Player player = userToPlayer(message);
        if (player == null) {
            return;
        }

        if (lengthCheck(message, 1)) {
            descriptionGenerator(message, player);
        } else {
            move(message, player);
        }

    }

    private void descriptionGenerator(Message message, Player player) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        if (player.getLocation() instanceof Region thisRegion) {

            Set<Region> regionList = Map.getMap().getRegions();
            Set<Subarea> subareaList = thisRegion.getSubareas();

            builder.title("Вы находитесь в " + player.getLocationName());
            builder.image(player.getLocation().getImage());

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("**Вы можете перейти в другой регион: **\n");
            for (Region region : regionList) {
                stringBuilder.append(region.getName()).append("\n");
            }
            stringBuilder.append("\n");

            stringBuilder.append("**Вы можете перейти в локацию " + thisRegion.getName() + ": **\n");
            for (Subarea subarea : subareaList) {
                stringBuilder.append(subarea.getName()).append("\n");
            }

            builder.description(stringBuilder.toString());
            message.getChannel().block().createMessage(builder.build()).block();


        }

        if (player.getLocation() instanceof Subarea thisSubarea) {

            Region thisRegion = null;
            for (Region region : Map.getMap().getRegions()) {
                if (region.getSubareas().contains(thisSubarea)) {
                    thisRegion = region;
                }
            }
            if (thisRegion == null) {
                builder.title("Локация в регионе не найдена");
                message.getChannel().block().createMessage(builder.build()).block();
                message.delete().block();
                return;
            }

            Set<Subarea> regionSubareas = thisRegion.getSubareas();
            Set<Subarea> subareas = thisSubarea.getSubareas();
            Set<Action> actions = thisSubarea.getActions();

            builder.title("Вы находитесь в " + player.getLocationName());
            builder.image(player.getLocation().getImage());

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("**Вы можете вернуться в: ").append("**\n").append(thisRegion.getName()).append("\n\n");

            stringBuilder.append("**Вы можете перейти в локацию: **\n");
            for (Subarea subarea : regionSubareas) {
                stringBuilder.append(subarea.getName()).append("\n");
            }
            stringBuilder.append("\n");

            if (!subareas.isEmpty()) {
                stringBuilder.append("**Вы можете пройти к: **\n");
                for (Subarea subarea : subareas) {
                    stringBuilder.append(subarea.getName()).append("\n");
                }
                stringBuilder.append("\n");
            }

            if (!actions.isEmpty()) {
                stringBuilder.append("**Вы можете заняться: **\n");
                for (Action action : actions) {
                    stringBuilder.append(action.getName()).append("\n");
                }
            }

            builder.description(stringBuilder.toString());
            message.getChannel().block().createMessage(builder.build()).block();

        }

        message.delete().block();

    }

    private void move(Message message, Player player) {

        String thisLocationName = message.getContent().replaceFirst("!" + getName() + " ", "");

        for (Location location : Map.getMap().getLocations()) {
            if (location.getName().equalsIgnoreCase(thisLocationName)) {

                player.setLocationName(location);
                Save.getSave().saveFile(player);
                descriptionGenerator(message, player);
                return;

            }
        }

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title("Локация " + thisLocationName + " не найдена");
        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();
    }

    public static MoveCommand getMoveCommand() {
        if (moveCommand == null) {
            moveCommand = new MoveCommand();
        }
        return moveCommand;
    }

}
