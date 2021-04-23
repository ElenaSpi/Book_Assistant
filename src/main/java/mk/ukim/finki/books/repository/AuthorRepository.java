package mk.ukim.finki.books.repository;

import mk.ukim.finki.books.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author getAuthorById(Long id);
    Author getAllByName(String name);
    @Transactional
    void deleteById(Long id);
}