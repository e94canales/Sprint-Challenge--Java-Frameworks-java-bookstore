package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.FoundationApplication;
import com.lambdaschool.foundation.models.Author;
import com.lambdaschool.foundation.models.Book;
import com.lambdaschool.foundation.models.Section;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FoundationApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void A_findBookById() {
        Book book = bookService.findBookById(78);
        assertEquals("Digital Fortess", book.getBooktitle());
    }

    @Test
    public void B_save() {

        Section s4 = new Section("BS 2");

        Book b4 = new Book("Do 2", "2378287", 2021, s4);
        Book b4Save = bookService.save(b4);
        assertEquals("Do 2", b4Save.getBooktitle());
    }

    @Test
    public void C_findAllBooks() {
        assertEquals(6, bookService.findAllBooks().size());
    }

    @Test
    public void D_deleteBookById() {
        bookService.deleteBookById(80);
        assertEquals(5, bookService.findAllBooks().size());
    }
}