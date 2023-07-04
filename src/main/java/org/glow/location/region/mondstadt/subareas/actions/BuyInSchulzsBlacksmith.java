package org.glow.location.region.mondstadt.subareas.actions;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.Main;
import org.glow.commands.RPGCommands.BuyCommand;
import org.glow.fileManager.Save;
import org.glow.item.Armor;
import org.glow.item.Item;
import org.glow.item.lefthand.Shield;
import org.glow.item.righthand.Weapon;
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
import org.glow.location.region.mondstadt.subareas.SchulzsBlacksmith;
import org.glow.message.Characteristic;
import org.glow.message.Parameters;
import org.glow.message.TextManager;
import org.glow.person.PersonManager;
import org.glow.person.Player;

import java.util.ArrayList;
import java.util.List;

public class BuyInSchulzsBlacksmith extends Action {

    private static final List<Weapon> shopWeaponList = List.of(new DullBlade(), new SilverSword());
    private static final List<Shield> shopShieldList = List.of(new IronShield(), new WhiteIronShield());
    private static final List<Armor> shopArmorList = List.of(new IronHeadArmor(), new IronBodyArmor(), new IronLegArmor(),
            new WhiteIronHeadArmor(), new WhiteIronBodyArmor(), new WhiteIronLegArmor());

    public BuyInSchulzsBlacksmith(Message message, Player player) {
        super(message, player);
        setName(Main.systems.commandPrefix + BuyCommand.getBuyCommand().getName());
        setDesctiption("Купить оружие или броню");
    }

    @Override
    public void startAction() {

        if (getMessage().getContent().split(" ").length == 1) {

            showProduct();

        } else {

            String wantToBuyProductName = getMessage().getContent().replaceFirst(getName() + " ", "");
            buyProduct(wantToBuyProductName);

        }

    }

    private void showProduct() {

        String description = """
                **Вы можете приобрести:**
                
                Оружие:
                %s | %s %s | %s %s
                %s | %s %s | %s %s
                
                Щиты:
                %s | %s %s | %s %s
                %s | %s %s | %s %s
                
                Броню:
                %s | %s %s | %s %s
                %s | %s %s | %s %s
                %s | %s %s | %s %s
                
                %s | %s %s | %s %s
                %s | %s %s | %s %s
                %s | %s %s | %s %s
                """;

        description = String.format(description,
                shopWeaponList.get(0).getName(), shopWeaponList.get(0).getAttack(), Characteristic.ATTACK.getName(), shopWeaponList.get(0).getPrice(), Parameters.COINS.getName(),
                shopWeaponList.get(1).getName(), shopWeaponList.get(1).getAttack(), Characteristic.ATTACK.getName(), shopWeaponList.get(1).getPrice(), Parameters.COINS.getName(),

                shopShieldList.get(0).getName(), shopShieldList.get(0).getArmor(), Characteristic.DEFEND.getName(), shopShieldList.get(0).getPrice(), Parameters.COINS.getName(),
                shopShieldList.get(1).getName(), shopShieldList.get(1).getArmor(), Characteristic.DEFEND.getName(), shopShieldList.get(1).getPrice(), Parameters.COINS.getName(),

                shopArmorList.get(0).getName(), shopArmorList.get(0).getArmor(), Characteristic.DEFEND.getName(), shopArmorList.get(0).getPrice(), Parameters.COINS.getName(),
                shopArmorList.get(1).getName(), shopArmorList.get(1).getArmor(), Characteristic.DEFEND.getName(), shopArmorList.get(1).getPrice(), Parameters.COINS.getName(),
                shopArmorList.get(2).getName(), shopArmorList.get(2).getArmor(), Characteristic.DEFEND.getName(), shopArmorList.get(2).getPrice(), Parameters.COINS.getName(),

                shopArmorList.get(3).getName(), shopArmorList.get(3).getArmor(), Characteristic.DEFEND.getName(), shopArmorList.get(3).getPrice(), Parameters.COINS.getName(),
                shopArmorList.get(4).getName(), shopArmorList.get(4).getArmor(), Characteristic.DEFEND.getName(), shopArmorList.get(4).getPrice(), Parameters.COINS.getName(),
                shopArmorList.get(5).getName(), shopArmorList.get(5).getArmor(), Characteristic.DEFEND.getName(), shopArmorList.get(5).getPrice(), Parameters.COINS.getName());

        sendMessageInChannel(SchulzsBlacksmith.getSchulzsBlacksmith().getName(), description);
    }

    private void buyProduct(String wantToBuyProductName) {

        List<Item> shopItemsList = new ArrayList<>();
        shopItemsList.addAll(shopWeaponList);
        shopItemsList.addAll(shopArmorList);
        shopItemsList.addAll(shopShieldList);

        for (Item item : shopItemsList) {

            if (wantToBuyProductName.equalsIgnoreCase(item.getName())) {
                itemFind(item);
                return;
            }

        }

        sendMessageInChannel("Предмет " + wantToBuyProductName + " не найден");

    }

    private void itemFind(Item item) {

        if (getPlayer().getCoins() < item.getPrice()) {
            sendMessageInChannel(PersonManager.getInstance().getPersonName(getPlayer()) + " у вас недостаточно :pig2:");
            return;
        }

        if (getPlayer().getInventory().getBag().size() == getPlayer().getInventory().getBagSize()) {
            sendMessageInChannel(PersonManager.getInstance().getPersonName(getPlayer()) + " ваша сумка заполнена");
            return;
        }

        getPlayer().setCoins(getPlayer().getCoins() - item.getPrice());
        getPlayer().getInventory().getBag().add(item);
        Save.getSave().saveFile(getPlayer());

        String title = (PersonManager.getInstance().getPersonName(getPlayer()) + ", вы приобрели " + item.getName());
        sendMessageInChannel(title, TextManager.getInstance().getPlayerParameters(getPlayer()));

    }

}
