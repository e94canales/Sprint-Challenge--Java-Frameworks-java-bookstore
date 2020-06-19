package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.foundation.models.Book;
import com.lambdaschool.foundation.models.Section;
import com.lambdaschool.foundation.models.Wrote;
import com.lambdaschool.foundation.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book findBookById(long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Book id " + id + " not found"));

    }

    @Transactional
    @Override
    public Book save(Book book) {
        Book newBook = new Book();

        if (book.getBookid() != 0) {
            Book existingBook = bookRepository.findById(book.getBookid())
                    .orElseThrow(() -> new ResourceNotFoundException("Book id " + book.getBookid() + " not found!"));


            newBook.setBookid(book.getBookid());
        }

        newBook.setBooktitle(book.getBooktitle());
        newBook.setIsbn(book.getIsbn());
        newBook.setCopy(book.getCopy());
        newBook.setSection(new Section(book.getSection().getSectionname()));

        List<Wrote> list = new ArrayList<>();
        for (Wrote wr : book.getAuthors())
        {
            list.add(wr);
          newBook.setWrotes(list);
        }

        return bookRepository.save(newBook);
    }

    @Override
    public List<Book> findAllBooks() {
        List<Book> bookList = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(bookList::add);
        return bookList;
    }

    @Transactional
    @Override
    public void deleteBookById(long id) {
        Book bookToDel = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not found for Book"));

        bookRepository.delete(bookToDel);
    }
}
