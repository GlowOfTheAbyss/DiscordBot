package org.glow;

import org.glow.filemanager.Load;
import org.glow.location.Map;
import org.glow.timeaction.TimeAction;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Systems {

    public void start() {

        Map.createMap();
        Load.getLoad().loadFile();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1, r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });

        executorService.scheduleAtFixedRate(TimeAction.getTimeAction(), 0, 30, TimeUnit.MINUTES);

    }

}
