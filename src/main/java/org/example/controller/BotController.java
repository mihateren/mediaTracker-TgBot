package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bot.command.Commands;
import org.example.service.BotService;
import org.telegram.telegrambots.meta.api.objects.message.Message;

@Slf4j
@RequiredArgsConstructor
public class BotController {

    private final BotService botService;

    public void handleMessage(Message message) {
        String chatId = message.getChatId().toString();
        String text = message.getText();

        if (text == null) {
            return;
        }

        Commands command = Commands.fromString(text);

        if (command != null) {
            switch (command) {
                case START:
                    botService.sendTextMessage(chatId, "Привет! Это телеграм бот для трекинга сериалов.");
                    break;
                case HELP:
                    botService.sendHelpMessage(chatId);
                    break;
                case GET_ALL_SERIES:
                    botService.sendTextMessage(chatId, "Вот список всех сериалов...");
                    break;
                case GET_ALL_FILMS:
                    botService.sendTextMessage(chatId, "Вот список всех фильмов...");
                    break;
                case ADD:
                    botService.sendTextMessage(chatId, "Добавление нового фильма...");
                    break;
                case EDIT:
                    botService.sendTextMessage(chatId, "Редактирование фильма...");
                    break;
                case DELETE:
                    botService.sendTextMessage(chatId, "Удаление фильма...");
                    break;
                default:
                    botService.sendTextMessage(chatId, "Неизвестная команда.");
            }
        } else {
            botService.sendTextMessage(chatId, "Неизвестная команда.");
        }
    }
}
