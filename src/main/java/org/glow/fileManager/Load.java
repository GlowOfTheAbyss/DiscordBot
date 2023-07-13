package org.glow.fileManager;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glow.Main;
import org.glow.Systems;
import org.glow.person.PersonManager;
import org.glow.person.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Load {

    private static Load load;
    final private Path path;

    private Load() {
        path = Main.getSystems().getSavesPath();
    }

    public void loadFile() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try (DirectoryStream<Path> files = Files.newDirectoryStream(path)) {

            for (Path filePath : files) {
                if (!Files.exists(filePath)) {
                    return;
                }

                Player player = objectMapper.readValue(new File(filePath.toUri()), Player.class);
                PersonManager.getInstance().addPlayer(player);

            }


        } catch (IOException exception) {
            System.out.println("Ошибка при загрузке экземпляров класса Player");
        }


    }

    public static Load getLoad() {
        if (load == null) {
            load = new Load();
        }
        return load;
    }

}
