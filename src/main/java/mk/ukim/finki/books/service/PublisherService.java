package mk.ukim.finki.books.service;

import mk.ukim.finki.books.model.Publisher;
import java.util.List;
import java.util.Optional;

public interface PublisherService {
    List<Publisher> findAll();
    Optional<Publisher> findAllById(Long id);
    Publisher save(String name);
    void deleteById(Long id);
}
