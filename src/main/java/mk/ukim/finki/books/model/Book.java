package mk.ukim.finki.books.model;
import lombok.*;
import mk.ukim.finki.books.model.enumerations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String name;
    private Integer numPages;

    @ManyToOne
    private Publisher publisher;

    @ManyToOne
    private Author author;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToMany
    private List<Rating> ratings;

    @ManyToMany
    private List<Student> students;

    public Book(String name, Integer numPages, Author author, Publisher pub, Type type) {
        this.ID = (long) (Math.random() * 1000);
        this.name = name;
        this.numPages = numPages;
        this.type = type;
        this.publisher = pub;
        this.author = author;
        this.students = new ArrayList<>();
    }

    public Book(String name, Integer numPages, List<Student> students, Author author, Publisher pub, Type type, List<Rating> ratings) {
        this.ID = (long) (Math.random() * 1000);
        this.name = name;
        this.numPages = numPages;
        this.author = author;
        this.publisher = pub;
        this.type = type;
        this.ratings = new ArrayList<>();
        this.students = students;
    }
}