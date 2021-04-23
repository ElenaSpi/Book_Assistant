package mk.ukim.finki.books.repository;

import mk.ukim.finki.books.model.Author;
import mk.ukim.finki.books.model.Book;
import mk.ukim.finki.books.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(Author author);
    Optional<Book> findByID(Long id);
    @Transactional
    void deleteByID(Long id);
    Optional<Book> findAllByName(String name);
    List<Book> findByPublisher(Publisher publisher);
}