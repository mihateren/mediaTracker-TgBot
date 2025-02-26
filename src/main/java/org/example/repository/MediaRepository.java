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
        } catch (HibernateException exception) {
            log.error("Error saving media", exception);
            throw exception; 
        }
    }

    public Media getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Media media = session.get(Media.class, id);
            if (media != null) {
                 Hibernate.initialize(media.getGenres()); 
            }
            return media;
        } catch (HibernateException exception) {
            log.error("Error fetching media by ID", exception);
            throw exception;
        }
    }

    public List<Media> getAllSeries() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Media where type = 'SERIES'", Media.class).list();
        } catch (HibernateException exception) {
            log.error("Error fetching all media", exception);
            throw exception;
        }
    }

    public List<Media> getAllFilms() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Media where type = 'FILM'", Media.class).list();
        } catch (HibernateException exception) {
            log.error("Error fetching all media", exception);
            throw exception;
        }
    }

    public void edit(Media media) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(media);
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            log.error("Error updating media", exception);
            throw exception;
        }
    }

    public void delete(Media media) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(media);
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            log.error("Error deleting media", exception);
            throw exception;
        }
    }
}
