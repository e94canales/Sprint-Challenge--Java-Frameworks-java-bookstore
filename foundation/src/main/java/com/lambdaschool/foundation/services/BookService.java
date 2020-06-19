package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Book;

import java.util.List;

public interface BookService {
    Book findBookById(long id);

    void deleteBookById(long id);

    List<Book> findAllBooks();

    Book save(Book book);
}
