package mk.ukim.finki.books.web;

import mk.ukim.finki.books.model.Author;
import mk.ukim.finki.books.model.Book;
import mk.ukim.finki.books.model.Rating;
import mk.ukim.finki.books.model.Student;
import mk.ukim.finki.books.model.enumerations.Type;
import mk.ukim.finki.books.repository.AuthorRepository;
import mk.ukim.finki.books.repository.BookRepository;
import mk.ukim.finki.books.repository.RatingRepository;
import mk.ukim.finki.books.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/charts")
public class ChartController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final RatingRepository ratingRepository;

    public ChartController(BookRepository bookRepository, AuthorRepository authorRepository, RatingRepository ratingRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.ratingRepository = ratingRepository;
    }

    @GetMapping
    public String getChartsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        return "charts";
    }


    @GetMapping("/authorsChart")
    public String getAuthorChart(Model model) {
        List<Author> authors = authorRepository.findAll();
        // Key: Author
        // Value: Number of books by author
        Map<String, Integer> map = new LinkedHashMap<>();

        for(Author author : authors) {
            List<Book> booksByAuthor = bookRepository.findByAuthor(author);
            int numBooksByAuthor = booksByAuthor.size();
            String name = author.getName() + " " + author.getSurname();
            map.put(name, numBooksByAuthor);
        }
        model.addAttribute("map", map);
        return "authorsChart";
    }

    @GetMapping("/readersChart")
    public String getReaderChart(Model model) {
        // Key: Book
        // Value: Number of students per book
        Map<String, Integer> bookMap = new LinkedHashMap<>();
        List<Book> books = bookRepository.findAll();

        for(Book book : books) {
            int numReaders = book.getStudents().size();
            String name = book.getName();
            bookMap.put(name, numReaders);
        }
        model.addAttribute("bookMap", bookMap);
        return "readersChart";
    }

    @GetMapping("/ratingsChart")
    public String getRatingsChart(Model model) {
        Map<String, Integer> bookMap = new LinkedHashMap<>();

        List<Book> books = bookRepository.findAll();
        for(Book book : books) {
            List<Rating> ratings = book.getRatings();
            int max = 0;
            for(Rating rating : ratings) {
                if(rating.getNumber() > max) {
                    max = rating.getNumber();
                }
            }
            String name = book.getName();
            bookMap.put(name, max);
        }

        model.addAttribute("bookMap", bookMap);
        return "ratingsChart";

    }

    @GetMapping("/bookTypesChart")
    public String getBookTypesChart(Model model) {
        List<Book> books = bookRepository.findAll();
        int eBooks = 0;
        int paperBooks = 0;
        int audioBooks = 0;

        for(Book book : books) {
            if (book.getType().equals(Type.E_BOOK)) {
                eBooks++;
            }
            if (book.getType().equals(Type.AUDIO_BOOK)) {
                audioBooks++;
            }
            if (book.getType().equals(Type.PAPER_BOOK)) {
                paperBooks++;
            }
        }

        model.addAttribute("ebook", eBooks);
        model.addAttribute("audio", audioBooks);
        model.addAttribute("paper", paperBooks);
        return "pieChart";
    }
}