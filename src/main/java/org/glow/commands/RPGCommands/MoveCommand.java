package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.commands.Command;
import org.glow.fileManager.Save;
import org.glow.location.*;
import org.glow.person.Player;

import java.util.List;

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

        if (Region.class.isAssignableFrom(player.getLocation().getClass())) {
            Region thisRegion = (Region) player.getLocation();
            List<Region> regionList = Map.getMap().getRegions();
            List<Subarea> subareaList = Map.getMap().getSubareas(thisRegion);

            builder.title("Вы находитесь в " + player.getLocation().getName());
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

        if (Subarea.class.isAssignableFrom(player.getLocation().getClass())) {
            Subarea thisSubarea = (Subarea) player.getLocation();
            Region region = Map.getMap().getRegionFromSubarea(thisSubarea);
            List<Subarea> subareasList = Map.getMap().getSubareas(region);
            List<PointsOfInterest> pointsOfInterestsList = Map.getMap().getPointsOfInterests(thisSubarea);

            builder.title("Вы находитесь в " + player.getLocation().getName());
            builder.image(player.getLocation().getImage());

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("**Вы можете вернуться в: ").append("**\n").append(region.getName()).append("\n");

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

        if (PointsOfInterest.class.isAssignableFrom(player.getLocation().getClass())) {
            PointsOfInterest thisPointsOfInterest = (PointsOfInterest) player.getLocation();
            Subarea subarea = Map.getMap().getSubareaFromPointsOfInterest(thisPointsOfInterest);
            List<PointsOfInterest> pointsOfInterestsList = Map.getMap().getPointsOfInterests(subarea);
            List<Action> actionsList = Map.getMap().getActions(thisPointsOfInterest);

            builder.title("Вы находитесь в " + player.getLocation().getName());
            builder.image(player.getLocation().getImage());

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("**Вы можете вернуться в: ").append("**\n").append(subarea.getName()).append("\n").append("\n");

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

        String thisLocationName = message.getContent().replaceFirst("!move ", "");

        for (Location location : Map.getLocations()) {
            if (location.getName().equalsIgnoreCase(thisLocationName)) {

                player.setLocation(location);
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
