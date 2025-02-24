package org.example.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MediaType {
    FILM("Фильм"),
    SERIES("Сериал");

    private final String displayName;

}
