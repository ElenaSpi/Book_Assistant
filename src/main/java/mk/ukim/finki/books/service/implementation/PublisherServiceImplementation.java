package mk.ukim.finki.books.service.implementation;

import mk.ukim.finki.books.model.Publisher;
import mk.ukim.finki.books.model.exceptions.PublisherAlreadyExists;
import mk.ukim.finki.books.model.exceptions.IllegalArgumentsException;
import mk.ukim.finki.books.repository.PublisherRepository;
import mk.ukim.finki.books.service.PublisherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImplementation implements PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherServiceImplementation(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Optional<Publisher> findAllById(Long id) {
        return publisherRepository.findById(id);
    }

    @Override
    public Publisher save(String name) {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentsException();
        }

        Publisher publisher = this.publisherRepository.getAllByName(name);
        if (publisher != null){
            throw  new PublisherAlreadyExists();
        }
        return publisherRepository.save(new Publisher(name));
    }

    @Override
    public void deleteById(Long id) {
        this.publisherRepository.deleteById(id);
    }
}
