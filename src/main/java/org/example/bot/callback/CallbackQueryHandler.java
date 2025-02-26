package org.example.bot.callback;

import lombok.RequiredArgsConstructor;
import org.example.model.Media;
import org.example.repository.MediaRepository;
import org.example.service.BotService;
import org.example.service.MediaService;
import org.example.utils.MessageFormattingUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public class CallbackQueryHandler {

    private final MediaService mediaService = new MediaService();
    private final String apiKey;
    private final BotService botService;

    public CallbackQueryHandler(String apiKey) {
        this.apiKey = apiKey;
        this.botService = new BotService(apiKey);
    }


    public void handleCallback(CallbackQuery callbackQuery) {
        String data = callbackQuery.getData();
        String chatId = callbackQuery.getMessage().getChatId().toString();

        switch (data.split("_")[0]) {
            case "media":
                Long mediaId = Long.parseLong(data.substring(6)); // media_123 -> 123
                Media foundedMedia = mediaService.getMediaById(mediaId);
                if (foundedMedia != null) {
                    String formattedMedia = MessageFormattingUtil.formatMedia(foundedMedia);
                    botService.sendImageWithCaptionMessage(chatId, foundedMedia.getCoverImageUrl(), formattedMedia);
                } else {
                    botService.sendTextMessage(chatId, "Ошибка: фильм/сериал не найден");
                }
                break;

            default:
                botService.sendTextMessage(chatId, "Ошибка: команда не найдена");
                break;
        }
    }

}
