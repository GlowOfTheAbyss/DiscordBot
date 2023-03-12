package org.glow.fileManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TokenAnalyzer {

    private final Path tokenPath = Path.of("src\\main\\resources\\token.txt").toAbsolutePath();
    private static final TokenAnalyzer tokenAnalyzer = new TokenAnalyzer();

    private TokenAnalyzer(){}

    public String findToken() {

        try {
            return Files.readAllLines(tokenPath).get(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static TokenAnalyzer getTokenAnalyzer() {
        return tokenAnalyzer;
    }
}
