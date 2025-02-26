package org.example.controller;

import org.example.model.Media;
import org.example.model.enums.MediaType;
import org.example.service.BotService;
import org.example.service.MediaService;
import org.example.utils.MessageFormattingUtil;

import java.util.List;

public class MediaController {

    private final MediaService mediaService;
    private final BotService botService;

    public MediaController(MediaService mediaService, String apiKey) {
        this.mediaService = mediaService;
        this.botService = new BotService(apiKey);
    }

    public List<Media> getAllMedia(MediaType typeOfMedia) {
        var mediaList = mediaService.getAllMedia(typeOfMedia);
        if (mediaList.isEmpty()) {
            return null;
        }
        return mediaList;
    }

    public void sendMedia(String chatId, Media media) {
        if (media.getCoverImageUrl() != null) {
            String mediaCaption = MessageFormattingUtil.formatMedia(media);
            botService.sendImageWithCaptionMessage(chatId, media.getCoverImageUrl(), mediaCaption);
        } else {
            botService.sendTextMessage(chatId, MessageFormattingUtil.formatMedia(media));
        }
    }

    public void sendEmptyMediaList(String chatId, MediaType typeOfMedia) {
        botService.sendTextMessage(chatId, MessageFormattingUtil.getNotFoundMessage(typeOfMedia));
    }


}
