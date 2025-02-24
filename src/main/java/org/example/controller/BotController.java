package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bot.command.Commands;
import org.example.model.Media;
import org.example.service.BotService;
import org.example.service.MediaService;
import org.example.utils.FormatUtil;
import org.telegram.telegrambots.meta.api.objects.message.Message;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class BotController {

    private final BotService botService;
    private final MediaService mediaService = new MediaService();

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
                    List<Media> series = mediaService.getAll();
                    for (Media media : series) {
                        if (media.getCoverImageUrl() != null) {
                            String mediaCaption = FormatUtil.formatMedia(media);
                            botService.sendImageWithCaptionMessage(chatId, media.getCoverImageUrl(), mediaCaption);
                        } else {
                            botService.sendTextMessage(chatId, FormatUtil.formatMedia(media));
                        }
                    }
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
