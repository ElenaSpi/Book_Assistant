package mk.ukim.finki.books.service;

import mk.ukim.finki.books.model.Author;
import mk.ukim.finki.books.model.Student;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findAuthorByID(Long id);
    Author save(String name, String surname);
    void deleteByID(Long id);
}
