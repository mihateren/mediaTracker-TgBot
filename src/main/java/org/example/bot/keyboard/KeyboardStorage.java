package org.example.bot.keyboard;

import org.example.bot.keyboard.factory.InlineKeyboardButtonFactory;
import org.example.bot.keyboard.factory.InlineKeyboardMarkupFactory;
import org.example.model.Media;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class KeyboardStorage {

    public static InlineKeyboardMarkup getMediaListKeyboard(List<Media> mediaList) {
        List<InlineKeyboardButton> buttons = new ArrayList<>();

        for (var media : mediaList) {
            InlineKeyboardButton button = InlineKeyboardButtonFactory.createButton(
                    media.getName(),
                    "media_" + media.getId()
            );
            buttons.add(button);
        }

        return InlineKeyboardMarkupFactory.createMultiButtonKeyboard(buttons);
    }
}
