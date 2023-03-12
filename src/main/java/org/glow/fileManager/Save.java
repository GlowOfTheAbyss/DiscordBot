package org.glow.fileManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.glow.person.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Save {

    private static final Save save = new Save();
    final private Path path = Path.of("src\\main\\resources\\saves").toAbsolutePath();

    private Save() {}

    public void saveFile(Player player) {

        ObjectMapper objectMapper = new ObjectMapper();

        Path savePath = path.resolve(player.getStringSnowflake() + ".json");

        try {
            objectMapper.writeValue(new File(savePath.toUri()), player);
        } catch (IOException e) {
            throw new RuntimeException("Save error");
        }

    }

    public static Save getSave() {
        return save;
    }
}
