package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.bot.command.Commands;

import org.example.model.Media;
import org.example.utils.MessageFormattingUtil;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

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
            SendMessage sendMessage = new SendMessage(chatId, text);
            sendMessage.enableMarkdown(true);
            telegramClient.execute(sendMessage);
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

    public void sendImageWithCaptionMessage(String chatId, String imageUrl, String caption) {
        try {
            SendPhoto sendPhotoWithCaption = new SendPhoto(chatId, new InputFile(imageUrl));
            sendPhotoWithCaption.setCaption(caption);
            sendPhotoWithCaption.setParseMode("Markdown");
            telegramClient.execute(sendPhotoWithCaption);
        } catch (TelegramApiException exception) {
            log.error("Ошибка при отправке фото с надписью: {}", exception.getMessage());
        }
    }

    public void sendKeyboardMessage(String chatId, InlineKeyboardMarkup inlineKeyboardMarkup) {
        try {
            SendMessage sendMessage = new SendMessage(chatId, "Вот фильмы: ");
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            sendMessage.enableMarkdown(true);
            telegramClient.execute(sendMessage);
        } catch (TelegramApiException exception) {
            log.error("Ошибка при отправке клавиатуры: {}", exception.getMessage());
        }
    }

    public void sendMediaMessage(String chatId, Media media) {
        if (media.getCoverImageUrl() != null) {
            String mediaCaption = MessageFormattingUtil.formatMedia(media);
            sendImageWithCaptionMessage(chatId, media.getCoverImageUrl(), mediaCaption);
        } else {
            sendTextMessage(chatId, MessageFormattingUtil.formatMedia(media));
        }
    }
}
