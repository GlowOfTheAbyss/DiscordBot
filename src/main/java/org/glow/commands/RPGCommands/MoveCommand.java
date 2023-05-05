package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.commands.Command;
import org.glow.fileManager.Save;
import org.glow.location.*;
import org.glow.location.region.liyue.Liyue;
import org.glow.location.region.mondstadt.Mondstadt;
import org.glow.location.region.mondstadt.subareas.CityMondstadt;
import org.glow.person.PersonManager;
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

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        if (playerInBattle(player)) {
            builder.title("Находясь в битве нельзя это использовать");
            message.getChannel().block().createMessage(builder.build()).block();
            message.delete().block();
            return;
        }

        if (lengthCheck(message, 1)) {
            descriptionGenerator(message, player);
        } else {
            move(message, player);
        }

    }

    private void descriptionGenerator(Message message, Player player) {

        if (PersonManager.getInstance().getPlayerLocation(player) instanceof Region thisRegion) {
            playerInRegion(message, player, thisRegion);
        }

        if (PersonManager.getInstance().getPlayerLocation(player) instanceof Subarea thisSubarea) {
            playerInSubarea(message, player, thisSubarea);
        }

    }

    private void playerInRegion(Message message, Player player, Region thisRegion) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        Set<Region> regions = Map.getInstance().getRegions();
        Set<Subarea> subareas = thisRegion.getSubareas();

        builder.title("Вы находитесь в " + player.getLocationName());
        builder.image(PersonManager.getInstance().getPlayerLocation(player).getImage());

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("**Вы можете перейти в другой регион: **\n");
        for (Region region : regions) {
            stringBuilder.append(region.getName()).append("\n");
        }
        stringBuilder.append("\n");

        stringBuilder.append("**Вы можете перейти в локацию " + thisRegion.getName() + ": **\n");
        for (Subarea subarea : subareas) {
            stringBuilder.append(subarea.getName()).append("\n");
        }

        builder.description(stringBuilder.toString());
        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    private void playerInSubarea(Message message, Player player, Subarea thisSubarea) {

        Region thisRegion = null;

        if (Map.getInstance().getLiyueLocations().contains(thisSubarea)) {
            thisRegion = Liyue.getLiyue();
        } else if (Map.getInstance().getMondstadtLocations().contains(thisSubarea)) {
            thisRegion = Mondstadt.getMondstadt();
        }

        if (thisRegion == null) {
            regionNotFound(message, player, thisSubarea);
        } else {
            regionFound(message, player, thisSubarea, thisRegion);
        }

    }

    private void regionFound(Message message, Player player, Subarea thisSubarea, Region thisRegion) {

        Set<Subarea> regionSubareas = thisRegion.getSubareas();
        Set<Subarea> subareas = thisSubarea.getSubareas();
        Set<Action> actions = thisSubarea.getActions();

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        builder.title("Вы находитесь в " + player.getLocationName());
        builder.image(PersonManager.getInstance().getPlayerLocation(player).getImage());

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
        message.delete().block();

    }

    private void regionNotFound(Message message, Player player, Subarea thisSubarea) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        Subarea headSubarea = null;

        if (Map.getInstance().getCityMondstadtLocations().contains(thisSubarea)) {
            headSubarea = CityMondstadt.getCityMondstadt();
        }

        if (headSubarea == null) {
            builder.title("Location Error");
            builder.description("Region or Head subarea not found");
            message.getChannel().block().createMessage(builder.build()).block();
            message.delete().block();
            return;
        }

        Set<Subarea> headSubareas = headSubarea.getSubareas();
        Set<Subarea> subareas = thisSubarea.getSubareas();
        Set<Action> actions = thisSubarea.getActions();

        builder.title("Вы находитесь в " + player.getLocationName());
        builder.image(PersonManager.getInstance().getPlayerLocation(player).getImage());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("**Вы можете вернуться в: ").append("**\n").append(headSubarea.getName()).append("\n\n");

        stringBuilder.append("**Вы можете перейти в локацию: **\n");
        for (Subarea subarea : headSubareas) {
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
        message.delete().block();

    }

    private void move(Message message, Player player) {

        String thisLocationName = message.getContent().replaceFirst("!" + getName() + " ", "");

        for (Location location : Map.getInstance().getLocations()) {
            if (location.getName().equalsIgnoreCase(thisLocationName)) {

                PersonManager.getInstance().setPlayerLocation(player, location);
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
