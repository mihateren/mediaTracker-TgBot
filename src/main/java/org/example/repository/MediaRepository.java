package org.example.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Media;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class MediaRepository {

    private final SessionFactory sessionFactory;

    public void save(Media media) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(media);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("Error saving media", e);
            throw e;  // Можно пробросить или обработать ошибку
        }
    }

    public Media getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Media media = session.get(Media.class, id);
            if (media != null) {
                 Hibernate.initialize(media.getGenres());  // Если нужно
            }
            return media;
        } catch (HibernateException e) {
            log.error("Error fetching media by ID", e);
            throw e;
        }
    }

    public List<Media> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Media", Media.class).list();
        } catch (HibernateException e) {
            log.error("Error fetching all media", e);
            throw e;
        }
    }

    public void update(Media media) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(media);  // Используем merge для правильной работы с сущностями
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("Error updating media", e);
            throw e;
        }
    }

    public void delete(Media media) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(media);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("Error deleting media", e);
            throw e;
        }
    }
}
