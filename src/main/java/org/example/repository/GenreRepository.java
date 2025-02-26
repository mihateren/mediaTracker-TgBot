package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.model.Genre;
import org.example.model.Genre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

@RequiredArgsConstructor
public class GenreRepository {

    private final SessionFactory sessionFactory;

    public void save(Genre genre) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(genre);
            session.getTransaction().commit();
        }
    }

    public Genre findById(String id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Genre.class, id);
        }
    }

    public List<Genre> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Genre").list();
        }
    }

    public void update(Genre genre) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(genre);
            session.getTransaction().commit();
        }
    }

    public void delete(Genre genre) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(genre);
            session.getTransaction().commit();
        }
    }

}
