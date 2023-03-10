package org.glow;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import org.glow.filemanager.Load;
import org.glow.filemanager.TokenAnalyzer;
import org.glow.location.Map;
import org.glow.timeaction.TimeAction;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static GatewayDiscordClient gateway;

    public static void main(String[] args) {

        final String token = TokenAnalyzer.getTokenAnalyzer().findToken();

        final DiscordClient client = DiscordClient.create(token);
        gateway = client.login().block();

        Map.createMap();
        Load.getLoad().loadFile();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1, r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });
        executorService.scheduleAtFixedRate(TimeAction.getTimeAction(), 0, 30, TimeUnit.MINUTES);

        gateway.on(MessageCreateEvent.class).subscribe(event -> {

            MessageReader.getMessageReader().readMessage(event.getMessage());

        });

        gateway.onDisconnect().block();

    }

}