package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.bot.TelegramBot;
import org.example.utils.DbMigrationUtil;
import org.example.utils.EnvLoader;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

@Slf4j
public class TelegramBotApplication {
    public static void main(String[] args) {
        String dbUrl = EnvLoader.getEnv("DB_URL");
        String dbUser = EnvLoader.getEnv("DB_USER");
        String dbPassword = EnvLoader.getEnv("DB_PASSWORD");

        try (var botApplication = new TelegramBotsLongPollingApplication()) {

            DbMigrationUtil.runMigrations(dbUrl, dbUser, dbPassword);

            String apiKey = getApiKey();
            botApplication.registerBot(apiKey, new TelegramBot(apiKey));
            log.info("Бот успешно запущен");

            Thread.currentThread().join();
        } catch (Exception exception) {
            log.error("Ошибка при запуске бота: {}", exception.getMessage());
        }
    }

    private static String getApiKey() {
        String apiKey = EnvLoader.getEnv("TELEGRAM_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalArgumentException("Ошибка: TELEGRAM_API_KEY не установлен.");
        }
        return apiKey;
    }
}
