package org.glow.location.region.mondstadt.subareas.pointsofinterest.cityMondstadt.actions;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.fileManager.Save;
import org.glow.location.Action;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.cityMondstadt.FavoniusCathedral;
import org.glow.magic.Magic;
import org.glow.magic.spells.Healing;
import org.glow.person.Player;

import java.util.List;

public class BuyInFavoniusCathedral extends Action {

    private static BuyInFavoniusCathedral buyInFavoniusCathedral;
    private static final List<Magic> shopSpellList = List.of(new Healing());

    private BuyInFavoniusCathedral() {
        setName("Купить | !buy");
    }

    @Override
    public void startAction(Message message, Player player) {

        if (message.getContent().split(" ").length == 1) {

            showProduct(message);

        } else {

            String wantToBuyProductName = message.getContent().replaceFirst("!buy ", "");
            buyProduct(message, player, wantToBuyProductName);

        }

    }

    private void showProduct(Message message) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
        builder.title(FavoniusCathedral.getFavoniusCathedral().getName());
        builder.description("**Вы можете приобрести:**\n" + "\n"
                + "Свитки скилов:\n"
                + shopSpellList.get(0).getSpellName() + " | " + shopSpellList.get(0).getCoastInMana() + " маны | " + shopSpellList.get(0).getPrice() + " :pig2:\n");

        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    private void buyProduct(Message message, Player player, String wantToBuyProductName) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        for (Magic magic : shopSpellList) {
            if (magic.getSpellName().equalsIgnoreCase(wantToBuyProductName)) {

                if (player.getCoins() < magic.getPrice()) {
                    builder.title(player.getName() + " у вас недостаточно :pig2:");
                    message.getChannel().block().createMessage(builder.build()).block();
                    message.delete().block();
                    return;
                }

                if (player.getSkillBook().getListSpell().size() == player.getSkillBook().getListSpellSize()) {
                    builder.title(player.getName() + " ваша книга заклинаний заполнена");
                    message.getChannel().block().createMessage(builder.build()).block();
                    message.delete().block();
                    return;
                }

                player.setCoins(player.getCoins() - magic.getPrice());
                player.getSkillBook().addListSpell(magic);
                Save.getSave().saveFile(player);

                builder.title(player.getName() + ", вы приобрели " + magic.getSpellName());
                builder.description(":pig2: " + player.getCoins() + "\n"
                        + "Энергия: " + player.getEnergy() + "\n"
                        + "Здоровье: " + player.getHealth() + "\n"
                        + "Мана: " + player.getMana() + "\n");

                message.getChannel().block().createMessage(builder.build()).block();
                message.delete().block();
                return;
            }
        }

        builder.title("Заклинание " + wantToBuyProductName + " не найдено");
        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    public static BuyInFavoniusCathedral getBuyFavoniusCathedral() {
        if (buyInFavoniusCathedral == null) {
            buyInFavoniusCathedral = new BuyInFavoniusCathedral();
        }
        return buyInFavoniusCathedral;
    }
}
