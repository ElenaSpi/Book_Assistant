package mk.ukim.finki.books.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer number;
    String message;

    public Rating(){}

    public Rating(Integer number, String message) {
        this.number = number;
        this.message = message;
    }
}