package org.glow.map.regions.mondstadt.subRegions.actions;

import discord4j.core.object.entity.Message;
import org.glow.Main;
import org.glow.commands.RPGCommands.BuyCommand;
import org.glow.fileManager.Save;
import org.glow.map.location.action.Action;
import org.glow.map.regions.mondstadt.subRegions.FavoniusCathedral;
import org.glow.magic.Spell;
import org.glow.magic.spells.Dawn;
import org.glow.magic.spells.ShiningMiracle;
import org.glow.message.MessageSender;
import org.glow.message.TextManager;
import org.glow.person.PersonManager;
import org.glow.person.Player;

import java.util.List;

public class BuyInFavoniusCathedral extends Action {

    private static final List<Spell> shopSpellList = List.of(new ShiningMiracle(), new Dawn());

    public BuyInFavoniusCathedral(Message message, Player player) {
        super(message, player);
        setName(Main.systems.commandPrefix + BuyCommand.getBuyCommand().getName());
        setDescription("Купить заклинание");
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
                **Вы можете приобрести**
                                
                Свитки скилов:
                %s | %s маны | %s моры
                %s | %s маны | %s моры
                """;

        MessageSender.getInstance().sendMessageInChannel(getMessage(), FavoniusCathedral.getFavoniusCathedral().getName(), String.format(description,
                shopSpellList.get(0).getSpellName(), shopSpellList.get(0).getCoastInMana(), shopSpellList.get(0).getPrice(),
                shopSpellList.get(1).getSpellName(), shopSpellList.get(1).getCoastInMana(), shopSpellList.get(1).getPrice()));

    }

    private void buyProduct(String wantToBuyProductName) {

        for (Spell spell : shopSpellList) {
            if (spell.getSpellName().equalsIgnoreCase(wantToBuyProductName)) {

                spellFind(spell);
                return;

            }
        }

        MessageSender.getInstance().sendMessageInChannel(getMessage(), "Заклинание " + wantToBuyProductName + " не найдено");

    }

    private void spellFind(Spell spell) {

        if (getPlayer().getCoins() < spell.getPrice()) {
            MessageSender.getInstance().sendMessageInChannel(getMessage(), PersonManager.getInstance().getPersonName(getPlayer()) + ", у вас недостаточно моры");
            return;
        }

        if (getPlayer().getSpellBook().getListSpell().size() == getPlayer().getSpellBook().getListSpellSize()) {
            MessageSender.getInstance().sendMessageInChannel(getMessage(), PersonManager.getInstance().getPersonName(getPlayer()) + ", ваша книга заклинаний заполнена");
            return;
        }

        for (Spell famousSpell : getPlayer().getSpellBook().getListSpell()) {
            if (famousSpell.getSpellName().equals(spell.getSpellName())) {
                MessageSender.getInstance().sendMessageInChannel(getMessage(), PersonManager.getInstance().getPersonName(getPlayer()) + ", вы уже владеете данным заклинанием");
                return;
            }
        }

        getPlayer().setCoins(getPlayer().getCoins() - spell.getPrice());
        getPlayer().getSpellBook().addListSpell(spell);
        Save.getSave().saveFile(getPlayer());

        String title = (PersonManager.getInstance().getPersonName(getPlayer()) + ", вы приобрели " + spell.getSpellName());
        MessageSender.getInstance().sendMessageInChannel(getMessage(), title, TextManager.getInstance().getPlayerParameters(getPlayer()));

    }

}
