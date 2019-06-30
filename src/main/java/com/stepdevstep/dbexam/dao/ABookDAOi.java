package com.stepdevstep.dbexam.dao;

import com.stepdevstep.dbexam.entities.Audiobook;

import java.util.List;

public interface ABookDAOi {
    int addBook(Audiobook book);
    int updateBook(Audiobook book);
    int deleteBook(int book_id);
    Audiobook getBook(int book_id);
    List<Audiobook> getBooks(String genre, String author,
                             String speaker, String name, String order);

}
