package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.bot.command.Commands;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class BotService {

    private final TelegramClient telegramClient;

    public BotService(String apiKey) {
        this.telegramClient = new OkHttpTelegramClient(apiKey);
    }

    public void sendTextMessage(String chatId, String text) {
        try {
            telegramClient.execute(new SendMessage(chatId, text));
        } catch (TelegramApiException exception) {
            log.error("Ошибка при отправке сообщения: {}", exception.getMessage());
        }
    }

    public void sendHelpMessage(String chatId) {
        StringBuilder helpMessage = new StringBuilder();
        for (Commands command : Commands.values()) {
            helpMessage.append(command.getCommandText()).append(" - ").append(command.getDescription()).append("\n");
        }
        sendTextMessage(chatId, helpMessage.toString());
    }
}
