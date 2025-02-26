package org.example.bot.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum Commands {

    START("/start", "Запуск бота"),
    HELP("/help", "Получить список команд"),
    ADD("/add", "Добавь фильм/сериал"),
    EDIT("/edit", "Редактировать фильм/сериал"),
    DELETE("/delete", "Удалить фильм/сериал"),
    GET_ALL_SERIES("/getAllSeries", "Получить все сериалы"),
    GET_ALL_FILMS("/getAllFilms", "Получить все фильмы"),
    GET_ALL_SERIES_AS_LIST("/getAllSeriesAsList", "Получить все сериалы списком"),
    GET_ALL_FILMS_AS_LIST("/getAllFilmsAsList", "Получить все фильмы списком");

    private final String commandText;
    private final String description;

    public static Commands fromString(String text) {
        return Arrays.stream(Commands.values())
                .filter(command -> command.getCommandText().equalsIgnoreCase(text))
                .findFirst()
                .orElse(null);
    }

}
