package mk.ukim.finki.books.service.implementation;

import mk.ukim.finki.books.model.Author;
import mk.ukim.finki.books.model.Rating;
import mk.ukim.finki.books.model.exceptions.AuthorAlreadyExistsException;
import mk.ukim.finki.books.model.exceptions.IllegalArgumentsException;
import mk.ukim.finki.books.repository.RatingRepository;
import mk.ukim.finki.books.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImplementation implements RatingService {
    private final RatingRepository ratingRepository;

    public RatingServiceImplementation(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }


    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating save(String message, Integer number) {
        if(message.isEmpty() || number == null) {
            throw new IllegalArgumentsException();
        }
        return ratingRepository.save(new Rating(number, message));
    }
}
