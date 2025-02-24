package org.example.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum GenreType {
    DRAMA("Драма"),
    THRILLER("Триллер"),
    COMEDY("Комедия"),
    ADVENTURE("Приключения"),
    FANTASY("Фэнтези"),
    MELODRAMA("Мелодрама"),
    HORROR("Хоррор"),
    DETECTIVE("Детектив"),
    DYSTOPIA("Антиутопия"),
    POST_APOCALYPTIC("Постапокалипсис"),
    SCIENCE_FICTION("Фантастика"),
    MYSTERY("Мистика"),
    ACTION("Экшн"),
    ROMANCE("Романтика"),
    DOCUMENTARY("Документальный"),
    MUSIC("Музыка"),
    ADVENTURE_DRAMA("Приключения, драма"),
    FAMILY("Семейный"),
    HISTORICAL("Исторический"),
    CRIME("Криминал"),
    SATIRICAL("Сатирический"),
    WAR("Военный"),
    SPORTS("Спортивный");

    private final String displayName;
}
