package org.glow.filemanager;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glow.person.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Load {

    private static final Load load = new Load();
    final private Path path = Path.of("src\\main\\resources\\saves").toAbsolutePath();

    private Load() {}

    public void loadFile() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try (DirectoryStream<Path> files = Files.newDirectoryStream(path)) {

            for (Path filePath : files) {

                if (!Files.exists(filePath)) {
                    return;
                }

                Player player = objectMapper.readValue(new File(filePath.toUri()), Player.class);
                Player.addPlayerList(player);

            }


        } catch (IOException exception) {
            throw new IllegalArgumentException(exception);
        }


    }

    public static Load getLoad() {
        return load;
    }
}
