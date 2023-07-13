package org.glow.fileManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.glow.Main;
import org.glow.person.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Save {

    private static Save save;
    final private Path path;

    private Save() {
        path = Main.getSystems().getSavesPath();
    }

    public void saveFile(Player player) {

        ObjectMapper objectMapper = new ObjectMapper();
        Path savePath = path.resolve(player.getSnowflake() + ".json");

        try {
            objectMapper.writeValue(new File(savePath.toUri()), player);
        } catch (IOException e) {
            System.out.println("Не получилось сохранить объект класса Player");
        }

    }

    public static Save getSave() {
        if (save == null) {
            save = new Save();
        }
        return save;
    }
}
