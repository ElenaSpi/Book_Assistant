package mk.ukim.finki.books.service.implementation;

import mk.ukim.finki.books.model.Author;
import mk.ukim.finki.books.model.exceptions.AuthorAlreadyExistsException;
import mk.ukim.finki.books.model.exceptions.IllegalArgumentsException;
import mk.ukim.finki.books.model.exceptions.StudentAlreadyExistsException;
import mk.ukim.finki.books.repository.AuthorRepository;
import mk.ukim.finki.books.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImplementation implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImplementation(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findAuthorByID(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author save(String name, String surname) {
        if(name.isEmpty() || surname.isEmpty()) {
            throw new IllegalArgumentsException();
        }

        Author author = authorRepository.getAllByName(name);
        if (author != null){
            throw  new AuthorAlreadyExistsException();
        }
        return authorRepository.save(new Author(name, surname));
    }

    @Override
    public void deleteByID(Long id) {
        this.authorRepository.deleteById(id);
    }
}