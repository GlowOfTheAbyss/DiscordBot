package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.commands.Command;
import org.glow.fileManager.Save;
import org.glow.location.*;
import org.glow.person.Player;

import java.util.List;
import java.util.Set;

public class MoveCommand extends Command {

    private static final MoveCommand moveCommand = new MoveCommand();

    private MoveCommand(){
        setName("move");
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

            stringBuilder.append("**Вы можете перейти в локацию мондштата: **\n");
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

            Set<Subarea> subareasList = thisRegion.getSubareas();
            Set<PointsOfInterest> pointsOfInterestsList = thisSubarea.getPointsOfInterests();

            builder.title("Вы находитесь в " + player.getLocationName());
            builder.image(player.getLocation().getImage());

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("**Вы можете вернуться в: ").append("**\n").append(thisRegion.getName()).append("\n");

            stringBuilder.append("**Вы можете перейти в локацию: **\n");
            for (Subarea subarea : subareasList) {
                stringBuilder.append(subarea.getName()).append("\n");
            }
            stringBuilder.append("\n");

            stringBuilder.append("**Вы можете пройти к: **\n");
            for (PointsOfInterest pointsOfInterest : pointsOfInterestsList) {
                stringBuilder.append(pointsOfInterest.getName()).append("\n");
            }

            builder.description(stringBuilder.toString());
            message.getChannel().block().createMessage(builder.build()).block();

        }

        if (player.getLocation() instanceof PointsOfInterest thisPointsOfInterest) {

            Subarea thisSubarea = null;
            for (Subarea subarea : Map.getMap().getSubareas()) {
                if (subarea.getPointsOfInterests().contains(thisPointsOfInterest)) {
                    thisSubarea = subarea;
                }
            }
            if (thisSubarea == null) {
                builder.title("Точка интереса в локации не найдена");
                message.getChannel().block().createMessage(builder.build()).block();
                message.delete().block();
                return;
            }

            Set<PointsOfInterest> pointsOfInterestsList = thisSubarea.getPointsOfInterests();
            Set<Action> actionsList = thisPointsOfInterest.getActions();

            builder.title("Вы находитесь в " + player.getLocationName());
            builder.image(player.getLocation().getImage());

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("**Вы можете вернуться в: ").append("**\n").append(thisSubarea.getName()).append("\n").append("\n");

            stringBuilder.append("**Вы можете пройти к: **\n");
            for (PointsOfInterest pointsOfInterest : pointsOfInterestsList) {
                stringBuilder.append(pointsOfInterest.getName()).append("\n");
            }
            stringBuilder.append("\n");

            stringBuilder.append("**Вы можете заняться: **\n");
            for (Action action : actionsList) {
                stringBuilder.append(action.getName()).append("\n");
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
        return moveCommand;
    }

}
