package org.example.bot.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class InlineButtonFactory {
    // Кнопка с текстом и колбэком
    public static InlineKeyboardButton createButton(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton(text);
        button.setCallbackData(callbackData);
        return button;
    }

    // Кнопка с url
    public static InlineKeyboardButton createUrlButton(String text, String url) {
        InlineKeyboardButton button = new InlineKeyboardButton(text);
        button.setUrl(url);
        return button;
    }

    // Клавиатура с одной кнопкой
    public static InlineKeyboardMarkup createSingleButtonKeyboard(InlineKeyboardButton button) {
        return createComplexKeyboard(List.of(List.of(button)));
    }

    // Клавиатура с несколькими кнопками
    public static InlineKeyboardMarkup createMultiButtonKeyboard(List<InlineKeyboardButton> buttons) {
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        rows.add(buttons);
        return createComplexKeyboard(rows);
    }

    // Клавиатура с несколькими рядами кнопок
    public static InlineKeyboardMarkup createComplexKeyboard(List<List<InlineKeyboardButton>> buttonRows) {
        List<InlineKeyboardRow> keyboard = new ArrayList<>();
        for (List<InlineKeyboardButton> buttonList : buttonRows) {
            InlineKeyboardRow row = new InlineKeyboardRow();
            row.addAll(buttonList);
            keyboard.add(row);
        }
        return new InlineKeyboardMarkup(keyboard);
    }
}
