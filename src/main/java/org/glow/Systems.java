package org.glow;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import org.glow.fileManager.Load;
import org.glow.fileManager.TokenAnalyzer;
import org.glow.location.LocationManager;
import org.glow.message.MessageReader;
import org.glow.timeaction.TimeAction;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Systems {

    public GatewayDiscordClient gateway;

    public String commandPrefix;

    public Systems() {
        String token = TokenAnalyzer.getTokenAnalyzer().findToken();
        gateway = DiscordClient.create(token).login().block();

        commandPrefix = "!";
    }

    public void start() {

        LocationManager.getInstance().generate();
        Load.getLoad().loadFile();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1, r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });

        executorService.scheduleAtFixedRate(TimeAction.getTimeAction(), 0, 30, TimeUnit.MINUTES);

        gateway.on(MessageCreateEvent.class).subscribe(event -> MessageReader.getMessageReader().readMessage(event.getMessage()));
        gateway.onDisconnect().block();

    }

}
