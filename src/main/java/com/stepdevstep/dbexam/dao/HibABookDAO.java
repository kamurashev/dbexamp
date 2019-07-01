package com.stepdevstep.dbexam.dao;

import com.stepdevstep.dbexam.entities.Audiobook;
import com.stepdevstep.dbexam.persistence.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HibABookDAO implements ABookDAOi {
    @Override
    public int addBook(Audiobook book) {
        int book_id;
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(book);
            book_id = book.getBook_id();
            session.getTransaction().commit();
        }
        return book_id;
    }

    @Override
    public int updateBook(Audiobook book) {
        int affected;
        final String hql = "UPDATE Audiobook SET name = :name, " +
                "genre = :genre, " +
                "author = :author, " +
                "speaker = :speaker, " +
                "duration = :duration, " +
                "views = :views," +
                "rate = :rate," +
                "description = :description, " +
                "cover = :cover, " +
                "content = :content " +
                "WHERE book_id = :book_id";

        try (Session session = HibernateUtil.getSession()) {
            Query query = session.createQuery(hql);
            query.setParameter("book_id", book.getBook_id());
            query.setParameter("name", book.getName());
            query.setParameter("genre", book.getGenre());
            query.setParameter("author", book.getAuthor());
            query.setParameter("speaker", book.getSpeaker());
            query.setParameter("duration", book.getDuration());
            query.setParameter("views", book.getViews());
            query.setParameter("rate", book.getRate());
            query.setParameter("description", book.getDescription());
            query.setParameter("cover", book.getCover());
            query.setParameter("content", book.getContent());
            session.beginTransaction();
            affected = query.executeUpdate();
            session.getTransaction().commit();
        }
        return affected;
    }

    @Override
    public int deleteBook(int book_id) {
        int affected;
        final String hql = "DELETE FROM Audiobook WHERE book_id = :book_id";
        try (Session session = HibernateUtil.getSession()) {
            Query query = session.createQuery(hql);
            query.setParameter("book_id", book_id);
            session.beginTransaction();
            affected = query.executeUpdate();
            session.getTransaction().commit();
        }
        return affected;
    }

    @Override
    public Audiobook getBook(int book_id) {
        System.out.println(book_id);
        Audiobook book;
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            book = session.get(Audiobook.class, book_id);
            session.getTransaction().commit();
        }
        return book;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Audiobook> getBooks(String genre, String author, String speaker, String name, String order) {
        order = order.equals("") ? "book_id" : order;
        List<Audiobook> books;
        try (Session session = HibernateUtil.getSession()) {
            String hql = "FROM Audiobook WHERE genre LIKE :genre AND " +
                    "author LIKE :author AND speaker LIKE :speaker " +
                    "AND name LIKE :name ORDER BY " + order + " DESC";
            Query query = session.createQuery(hql);
            query.setParameter("genre", "%" + genre + "%");
            query.setParameter("author", "%" + author + "%");
            query.setParameter("speaker", "%" + speaker + "%");
            query.setParameter("name", "%" + name + "%");
            session.beginTransaction();
            books = query.list();
            session.getTransaction().commit();
        }
        return books;
    }
}
