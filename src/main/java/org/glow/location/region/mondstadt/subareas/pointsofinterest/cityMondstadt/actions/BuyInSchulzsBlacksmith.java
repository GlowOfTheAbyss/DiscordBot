package org.glow.location.region.mondstadt.subareas.pointsofinterest.cityMondstadt.actions;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.fileManager.Save;
import org.glow.item.Armor;
import org.glow.item.Item;
import org.glow.item.Shield;
import org.glow.item.Weapon;
import org.glow.item.body.IronBodyArmor;
import org.glow.item.body.WhiteIronBodyArmor;
import org.glow.item.lefthand.IronShield;
import org.glow.item.lefthand.WhiteIronShield;
import org.glow.item.righthand.DullBlade;
import org.glow.item.righthand.SilverSword;
import org.glow.item.head.IronHeadArmor;
import org.glow.item.head.WhiteIronHeadArmor;
import org.glow.item.legs.IronLegArmor;
import org.glow.item.legs.WhiteIronLegArmor;
import org.glow.location.Action;
import org.glow.location.region.mondstadt.subareas.pointsofinterest.cityMondstadt.SchulzsBlacksmith;
import org.glow.person.Player;

import java.util.ArrayList;
import java.util.List;

public class BuyInSchulzsBlacksmith extends Action {

    private static BuyInSchulzsBlacksmith buyInSchulzsBlacksmith;

    private static final List<Weapon> shopWeaponList = List.of(new DullBlade(), new SilverSword());
    private static final List<Shield> shopShieldList = List.of(new IronShield(), new WhiteIronShield());
    private static final List<Armor> shopArmorList = List.of(new IronHeadArmor(), new IronBodyArmor(), new IronLegArmor(),
            new WhiteIronHeadArmor(), new WhiteIronBodyArmor(), new WhiteIronLegArmor());

    private BuyInSchulzsBlacksmith() {
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
        builder.title(SchulzsBlacksmith.getSchulzsBlacksmith().getName());
        builder.description("**Вы можете приобрести:**\n" + "\n"
                + "Оружие:\n"
                + shopWeaponList.get(0).getName() + " | " + shopWeaponList.get(0).getAttack() + " атака | " + shopWeaponList.get(0).getPrice() + " :pig2:\n"
                + shopWeaponList.get(1).getName() + " | " + shopWeaponList.get(1).getAttack() + " атака | " + shopWeaponList.get(1).getPrice() + " :pig2:\n" + "\n"

                + "Щиты:\n"
                + shopShieldList.get(0).getName() + " | " + shopShieldList.get(0).getArmor() + " защита | " + shopShieldList.get(0).getPrice() + " :pig2:\n"
                + shopShieldList.get(1).getName() + " | " + shopShieldList.get(1).getArmor() + " защита | " + shopShieldList.get(1).getPrice() + " :pig2:\n" + "\n"

                + "Броню:\n"
                + shopArmorList.get(0).getName() + " | " + shopArmorList.get(0).getArmor() + " защита | " + shopArmorList.get(0).getPrice() + " :pig2:\n"
                + shopArmorList.get(1).getName() + " | " + shopArmorList.get(1).getArmor() + " защита | " + shopArmorList.get(1).getPrice() + " :pig2:\n"
                + shopArmorList.get(2).getName() + " | " + shopArmorList.get(2).getArmor() + " защита | " + shopArmorList.get(2).getPrice() + " :pig2:\n" + "\n"

                + shopArmorList.get(3).getName() + " | " + shopArmorList.get(3).getArmor() + " защита | " + shopArmorList.get(3).getPrice() + " :pig2:\n"
                + shopArmorList.get(4).getName() + " | " + shopArmorList.get(4).getArmor() + " защита | " + shopArmorList.get(4).getPrice() + " :pig2:\n"
                + shopArmorList.get(5).getName() + " | " + shopArmorList.get(5).getArmor() + " защита | " + shopArmorList.get(5).getPrice() + " :pig2:\n");

        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    private void buyProduct(Message message, Player player, String wantToBuyProductName) {

        List<Item> shopItemsList = new ArrayList<>();
        shopItemsList.addAll(shopWeaponList);
        shopItemsList.addAll(shopArmorList);
        shopItemsList.addAll(shopShieldList);

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        for (Item items : shopItemsList) {

            if (wantToBuyProductName.equalsIgnoreCase(items.getName())) {

                if (player.getCoins() < items.getPrice()) {
                    builder.title(player.getName() + " у вас недостаточно :pig2:");
                    message.getChannel().block().createMessage(builder.build()).block();
                    message.delete().block();
                    return;
                }

                if (player.getInventory().getBag().size() == player.getInventory().getBagSize()) {
                    builder.title(player.getName() + " ваша сумка заполнена");
                    message.getChannel().block().createMessage(builder.build()).block();
                    message.delete().block();
                    return;
                }

                player.setCoins(player.getCoins() - items.getPrice());
                player.getInventory().getBag().add(items);
                Save.getSave().saveFile(player);

                builder.title(player.getName() + ", вы приобрели " + items.getName());
                builder.description(":pig2: " + player.getCoins() + "\n"
                        + "Энергия: " + player.getEnergy() + "\n"
                        + "Здоровье: " + player.getHealth() + "\n"
                        + "Мана: " + player.getMana() + "\n");

                message.getChannel().block().createMessage(builder.build()).block();
                message.delete().block();
                return;
            }

        }

        builder.title("Предмет " + wantToBuyProductName + " не найден");
        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    public static BuyInSchulzsBlacksmith getBuyInSchulzsBlacksmith() {
        if (buyInSchulzsBlacksmith == null) {
            buyInSchulzsBlacksmith = new BuyInSchulzsBlacksmith();
        }
        return buyInSchulzsBlacksmith;
    }

}
