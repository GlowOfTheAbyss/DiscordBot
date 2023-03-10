package org.glow;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import org.glow.filemanager.TokenAnalyzer;

public class Main {

    public static GatewayDiscordClient gateway;

    public static void main(String[] args) {

        final String token = TokenAnalyzer.getTokenAnalyzer().findToken();

        final DiscordClient client = DiscordClient.create(token);
        gateway = client.login().block();

        new Systems().start();

        gateway.on(MessageCreateEvent.class).subscribe(event -> MessageReader.getMessageReader().readMessage(event.getMessage()));

        gateway.onDisconnect().block();

    }

}