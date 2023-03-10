package org.glow.magic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import discord4j.core.object.entity.Message;
import org.glow.person.Player;

public class MagicCreated implements Magic {

    String spellName;
    int coastInMana;

    int price;

    public MagicCreated() {
    }

    public MagicCreated(String spellName, int coastInMana) {
        this.spellName = spellName;
        this.coastInMana = coastInMana;
    }

    @Override
    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }

    @Override
    public int getCoastInMana() {
        return coastInMana;
    }

    public void setCoastInMana(int coastInMana) {
        this.coastInMana = coastInMana;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @JsonIgnore
    @Override
    public void spellStart(Message message, Player player) {}
}
