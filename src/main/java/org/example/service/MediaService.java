package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Media;
import org.example.repository.MediaRepository;
import org.example.utils.HibernateUtil;

import java.util.List;

@RequiredArgsConstructor
public class MediaService {

    private final MediaRepository mediaRepository = new MediaRepository(HibernateUtil.getSessionFactory());

    public void save(Media media) {
        mediaRepository.save(media);
    }

    public Media getById(Long id) {
        return mediaRepository.getById(id);
    }

    public List<Media> getAll() {
        return mediaRepository.getAll();
    }

    public void delete(Media media) {
        mediaRepository.delete(media);
    }



}
