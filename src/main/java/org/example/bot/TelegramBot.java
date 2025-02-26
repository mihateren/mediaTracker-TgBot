package org.example.bot;

import org.example.bot.callback.CallbackQueryHandler;
import org.example.controller.BotController;
import org.example.service.BotService;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramBot implements LongPollingSingleThreadUpdateConsumer {

    private final BotController botController;
    private final CallbackQueryHandler callbackQueryHandler;

    public TelegramBot(String apiKey) {
        BotService telegramBotService = new BotService(apiKey);
        this.callbackQueryHandler = new CallbackQueryHandler(apiKey);
        this.botController = new BotController(telegramBotService);
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage()) {
            botController.handleMessage(update.getMessage());
        } else if (update.hasCallbackQuery()) {
             callbackQueryHandler.handleCallback(update.getCallbackQuery());
        }
    }
}
