package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;
    private String booktitle;
    @Column(nullable = false, unique = true)
    private String isbn;
    private int copy;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "book", allowSetters = true)
    private List<Wrote> authors = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "sectionid")
    @JsonIgnoreProperties("booksInSection")
    private Section section;

    public Book() {
    }

    public Book(String booktitle, String isbn, int copy) {
        this.booktitle = booktitle;
        this.isbn = isbn;
        this.copy = copy;
    }

    public Book(String booktitle, String isbn, int copy, Section section) {
        this.booktitle = booktitle;
        this.isbn = isbn;
        this.copy = copy;
        this.section = section;
    }

    public long getBookid() {
        return bookid;
    }

    public void setBookid(long bookid) {
        this.bookid = bookid;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getCopy() {
        return copy;
    }

    public void setCopy(int copy) {
        this.copy = copy;
    }

    public List<Wrote> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Wrote> authors) {
        this.authors = authors;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public void setWrotes(List<Wrote> wrotes)
    {
        this.authors = wrotes;

        for (Wrote wrote : this.authors)
            wrote.setBook(this);
    }
}
