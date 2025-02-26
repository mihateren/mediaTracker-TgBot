package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bot.command.Commands;
import org.example.model.enums.MediaType;
import org.example.service.BotService;
import org.example.service.MediaService;
import org.example.utils.MessageFormattingUtil;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


import java.util.ArrayList;

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
                    handleGetAllMedia(MediaType.SERIES, chatId, false);
                    break;
                case GET_ALL_FILMS:
                    handleGetAllMedia(MediaType.FILM, chatId, false);
                    break;
                case GET_ALL_SERIES_AS_LIST:
                    handleGetAllMedia(MediaType.SERIES, chatId, true);
                    break;
                case GET_ALL_FILMS_AS_LIST:
                    handleGetAllMedia(MediaType.FILM, chatId, true);
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

    private void handleGetAllMedia(MediaType typeOfMedia, String chatId, boolean asList) {
        String searchMessage = MessageFormattingUtil.getSearchMessage(typeOfMedia);
        botService.sendTextMessage(chatId, searchMessage);

        var mediaList = mediaService.getAllMedia(typeOfMedia);
        if (mediaList.isEmpty()) {
            String notFoundMessage = MessageFormattingUtil.getNotFoundMessage(typeOfMedia);
            botService.sendTextMessage(chatId, notFoundMessage);
            return;
        }

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup(new ArrayList<>());
        for (var media : mediaList) {
            if (asList) {
                keyboardMarkup = MessageFormattingUtil.getMediaKeyboard(keyboardMarkup, media);
                botService.sendKeyboardMessage(chatId, keyboardMarkup);
            } else {
                if (media.getCoverImageUrl() != null) {
                    String mediaCaption = MessageFormattingUtil.formatMedia(media);
                    botService.sendImageWithCaptionMessage(chatId, media.getCoverImageUrl(), mediaCaption);
                } else {
                    botService.sendTextMessage(chatId, MessageFormattingUtil.formatMedia(media));
                }
            }
        }

    }
}
