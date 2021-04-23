package mk.ukim.finki.books.service;

import mk.ukim.finki.books.model.Rating;
import mk.ukim.finki.books.model.Student;

import java.util.List;

public interface RatingService {
    List<Rating> findAll();
    Rating save(String message, Integer number);
}
