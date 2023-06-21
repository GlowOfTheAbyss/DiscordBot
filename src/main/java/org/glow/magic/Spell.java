package org.glow.magic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import discord4j.core.object.entity.Message;
import org.glow.person.Player;

public class Spell implements Castable {

    private String spellName;
    private int coastInMana;

    private int price;

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }

    public int getCoastInMana() {
        return coastInMana;
    }

    public void setCoastInMana(int coastInMana) {
        this.coastInMana = coastInMana;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @JsonIgnore
    @Override
    public void cast(Message message, Player player) {}

}
