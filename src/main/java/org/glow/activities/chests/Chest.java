package org.glow.activities.chests;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.item.Item;
import org.glow.person.PersonManager;
import org.glow.person.Player;

public abstract class Chest {

    private Message message;
    private Player player;
    private final EmbedCreateSpec.Builder builder;

    public Chest(Message message, Player player) {
        this.message = message;
        this.player = player;

        builder = EmbedCreateSpec.builder();
    }

    public abstract void openChest();

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    protected void coinsFindMessage(String title, int coins) {

        builder.title(title);
        builder.description(PersonManager.getInstance().getPersonName(player) + " находит " + coins + " :pig2:\n\n"
                            + ":pig2: " + player.getCoins() + "\n"
                            + "Энергия: " + player.getEnergy() + "\n"
                            + "Здоровье: " + player.getHealth() + "\n"
                            + "Мана: " + player.getMana() + "\n");

        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    protected void itemFindMessage(Item item) {

        builder.title(PersonManager.getInstance().getPersonName(player) + " нашел " + item.getName());
        builder.description(item.getName() + " добавлен в инвентарь");

        message.getChannel().block().createMessage(builder.build()).block();

    }

    protected void fullInventoryMessage(Item item) {
        builder.title(PersonManager.getInstance().getPersonName(player) + " находит " + item.getName() + ", но у него нет места в инвентаре что бы его забрать");
        builder.description("Предмет остается в сундуке");

        message.getChannel().block().createMessage(builder.build()).block();

    }

}
