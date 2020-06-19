package com.lambdaschool.foundation.repository;

import com.lambdaschool.foundation.models.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {


}
