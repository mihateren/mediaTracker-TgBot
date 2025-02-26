package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Media;
import org.example.model.enums.MediaType;
import org.example.repository.MediaRepository;
import org.example.utils.HibernateUtil;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MediaService {

    private final MediaRepository mediaRepository = new MediaRepository(HibernateUtil.getSessionFactory());

    public void save(Media media) {
        mediaRepository.save(media);
    }

    public List<Media> getAllMedia(MediaType typeOfMedia) {
        if (typeOfMedia == MediaType.FILM) {
            return mediaRepository.getAllFilms();
        } else if (typeOfMedia == MediaType.SERIES) {
            return mediaRepository.getAllSeries();
        }
        return List.of();
    }

    public Media getMediaById(Long id) {
        return mediaRepository.getById(id);
    }

    public void editMedia(Media media) {
        mediaRepository.edit(media);
    }

    public void delete(Media media) {
        mediaRepository.delete(media);
    }

}
