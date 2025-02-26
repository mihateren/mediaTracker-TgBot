package org.example.bot.keyboard;

import org.example.model.Media;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;


public class KeyboardStorage {
    public static InlineKeyboardMarkup getDetailsForMediaKeyboard(Media media) {
        InlineKeyboardButton detailsButton = InlineButtonFactory.createButton(
                media.getName(),
                "media_detail_" + media.getId()
        );
        return InlineButtonFactory.createSingleButtonKeyboard(detailsButton);
    }
}
