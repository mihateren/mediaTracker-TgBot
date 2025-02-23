package org.example.utils;

import io.github.cdimascio.dotenv.Dotenv;


public class EnvLoader {
    private static Dotenv dotenv = Dotenv.load();

    public static String getEnv(String key) {
        return dotenv.get(key);
    }
}
