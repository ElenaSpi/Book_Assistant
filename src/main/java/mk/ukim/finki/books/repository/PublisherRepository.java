package mk.ukim.finki.books.repository;

import mk.ukim.finki.books.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Publisher getAllByName(String name);
    @Transactional
    void deleteById(Long id);
}