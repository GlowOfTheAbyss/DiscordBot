package org.glow.actions;

import discord4j.core.object.entity.Message;
import org.glow.person.Person;

public class Battle {

    private Person attacker;
    private Person defender;
    private Message message;

    public Battle(Message message, Person attacker, Person defender) {
        this.attacker = attacker;
        this.defender = defender;
        this.message = message;
    }

    public void start() {



    }

}
