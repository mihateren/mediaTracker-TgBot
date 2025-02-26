package org.example.utils;

import org.example.model.Genre;
import org.example.model.Media;
import org.example.model.enums.MediaType;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class MessageFormattingUtil {

    public static String formatMedia(Media media) {
        StringBuilder formattedOutput = new StringBuilder();

        formattedOutput.append("*Название:* ").append(media.getName()).append("\n");

        if (media.getDescription() != null && !media.getDescription().isEmpty()) {
            formattedOutput.append("*Описание:* ").append(media.getDescription()).append("\n");
        }

        if (media.getReleaseCountry() != null && !media.getReleaseCountry().isEmpty()
                || media.getReleaseYear() != null) {
            formattedOutput.append("*Страна:* ").append(media.getReleaseCountry() != null ? media.getReleaseCountry() : "Не указана")
                    .append(" | *Год выпуска:* ").append(media.getReleaseYear() != null ? media.getReleaseYear() : "Не указан").append("\n");
        }

        if (media.getType() != null) {
            formattedOutput.append("*Тип:* ").append(media.getType().getDisplayName()).append("\n");
        }

        if (media.getGenres() != null && !media.getGenres().isEmpty()) {
            formattedOutput.append("*Жанры:* ");
            for (Genre genre : media.getGenres()) {
                formattedOutput.append(genre.getName()).append(", ");
            }
            formattedOutput.setLength(formattedOutput.length() - 2);
            formattedOutput.append("\n");
        }

        if (media.getCreatedAt() != null) {
            formattedOutput.append("*Дата создания:* ").append(media.getCreatedAt().toLocalDate()).append("\n");
        }

        return formattedOutput.toString();
    }


    public static String getSearchMessage(MediaType type) {
        return type == MediaType.FILM ? "Ищу фильмы..." : "Ищу сериалы...";
    }

    public static String getNotFoundMessage(MediaType type) {
        return type == MediaType.FILM ? "Список фильмов пуст." : "Список сериалов пуст.";
    }

    public static InlineKeyboardMarkup getMediaKeyboard(InlineKeyboardMarkup keyboardMarkup, Media media) {
        List<InlineKeyboardRow> rowsInline = new ArrayList<>(keyboardMarkup.getKeyboard());

        InlineKeyboardButton button = new InlineKeyboardButton(media.getName());
        button.setCallbackData("media_" + media.getId());

        InlineKeyboardRow row = new InlineKeyboardRow(List.of(button));
        rowsInline.add(row);

        return new InlineKeyboardMarkup(rowsInline);
    }


}
