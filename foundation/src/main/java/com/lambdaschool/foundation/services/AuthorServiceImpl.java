package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.foundation.models.Author;
import com.lambdaschool.foundation.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookService bookService;

    @Transactional
    @Override
    public Author save(Author author) {
        Author newAuthor = new Author();

        if (author.getAuthorid() != 0) {
            Author existingAuthor = authorRepository.findById(author.getAuthorid())
                    .orElseThrow(() -> new ResourceNotFoundException("Author id " + author.getAuthorid() + " not found!"));


            newAuthor.setAuthorid(author.getAuthorid());
        }

        newAuthor.setFirstname(author.getFirstname());
        newAuthor.setLastname(author.getLastname());


        return authorRepository.save(newAuthor);
    }

    @Override
    public List<Author> findAll() {
        List<Author> authorList = new ArrayList<>();
        authorRepository.findAll().iterator().forEachRemaining(authorList::add);

        return authorList;
    }
}

