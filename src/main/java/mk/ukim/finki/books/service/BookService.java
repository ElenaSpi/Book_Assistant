package mk.ukim.finki.books.service;

import mk.ukim.finki.books.model.Author;
import mk.ukim.finki.books.model.Book;
import mk.ukim.finki.books.model.Publisher;
import mk.ukim.finki.books.model.Student;
import mk.ukim.finki.books.model.enumerations.Rating;
import mk.ukim.finki.books.model.enumerations.Type;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listBooks();
    Optional<Book> findByID(Long id);
    List<Book> findByAuthor(Author author);
    Book edit(Long id, String name, Integer number, Author author, Publisher publisher, Type type);
    Book save(String name, Integer numPages, Author author, Publisher publisher, Type type);
    void deleteByID(Long id);
    Book addReader(String username, Long bookID);
    Book addRating(Long ratingID, Long bookID);
    List<Student> listReadersByBook(Long bookID);
    List<Book> findByPublisher(Publisher publisher);
}
