package org.glow.location.region.mondstadt.subareas.pointsofinterest.mondstadt.actions;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.fileManager.Save;
import org.glow.location.Action;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.mondstadt.FavoniusCathedral;
import org.glow.person.Player;

public class HealFavoniusCathedral extends Action {

    private static final HealFavoniusCathedral healFavoniusCathedral = new HealFavoniusCathedral();

    private HealFavoniusCathedral() {
        setName("Востановить здоровье | !heal");
    }

    @Override
    public void startAction(Message message, Player player) {

        if (message.getContent().split(" ").length == 1) {

            showInfo(message);

        } else {

            heal(message, player);

        }

    }

    private void showInfo(Message message) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title(FavoniusCathedral.getFavoniusCathedral().getName());
        builder.description("**Вы можете заплатить, что бы вас полечили:**\n" + "\n"
                + "10 :pig2: = 10 HP");

        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    private void heal(Message message, Player player) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        if (message.getContent().split(" ").length != 2) {
            builder.title("Неверное количество аргументов");
            message.getChannel().block().createMessage(builder.build()).block();
            message.delete().block();
            return;
        }

        int healCoast;
        try {
            healCoast = Integer.parseInt(message.getContent().split(" ")[1]);
        } catch (NumberFormatException exception) {
            builder.title("Не найдено число");
            message.getChannel().block().createMessage(builder.build()).block();
            message.delete().block();
            return;
        }

        if (player.getCoins() < healCoast) {
            builder.title(player.getName() + " у вас недостаточно :pig2:");
            message.getChannel().block().createMessage(builder.build()).block();
            message.delete().block();
            return;
        }

        player.setCoins(player.getCoins() - healCoast);
        player.setHealth(player.getHealth() + (healCoast));
        if (player.getHealth() > player.getMaxHealth()) {
            player.setHealth(player.getMaxHealth());
        }
        Save.getSave().saveFile(player);

        builder.title(player.getName() + " востановил " + healCoast + " поинтов здоровья");
        builder.description(":pig2: " + player.getCoins() + "\n"
                + "Энергия: " + player.getEnergy() + "\n"
                + "Здоровье: " + player.getHealth() + "\n"
                + "Мана: " + player.getMana() + "\n");
        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    public static HealFavoniusCathedral getHealFavoniusCathedral() {
        return healFavoniusCathedral;
    }
}
