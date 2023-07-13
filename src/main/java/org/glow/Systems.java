package org.glow;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import org.glow.fileManager.Load;
import org.glow.fileManager.TokenAnalyzer;
import org.glow.map.location.LocationManager;
import org.glow.message.MessageReader;
import org.glow.timeaction.TimeAction;

import java.nio.file.Path;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Systems {

    private final GatewayDiscordClient gateway;
    private final ScheduledExecutorService executorService;
    private final String commandPrefix;
    private final Path savesPath;

    public Systems() {
        String token = TokenAnalyzer.getTokenAnalyzer().findToken();
        gateway = DiscordClient.create(token).login().block();

        commandPrefix = "!";
        savesPath = Path.of("src\\main\\resources\\saves").toAbsolutePath();

        executorService = Executors.newScheduledThreadPool(1, r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });

    }

    public void start() {

        LocationManager.getInstance().generate();
        Load.getLoad().loadFile();

        executorService.scheduleAtFixedRate(TimeAction.getTimeAction(), 0, 30, TimeUnit.MINUTES);

        gateway.on(MessageCreateEvent.class)
                .subscribe(event -> MessageReader.getMessageReader().readMessage(event.getMessage()));
        gateway.onDisconnect().block();

    }

    public GatewayDiscordClient getGateway() {
        return gateway;
    }

    public String getCommandPrefix() {
        return commandPrefix;
    }

    public Path getSavesPath() {
        return savesPath;
    }

}
