package mk.ukim.finki.books.web;

import mk.ukim.finki.books.model.Book;
import mk.ukim.finki.books.model.Rating;
import mk.ukim.finki.books.repository.RatingRepository;
import mk.ukim.finki.books.service.BookService;
import mk.ukim.finki.books.service.RatingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/addRatings")
public class RatingController {
    private final BookService bookService;
    private final RatingService ratingService;

    public RatingController(BookService bookService, RatingService ratingService) {
        this.bookService = bookService;
        this.ratingService = ratingService;
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getRatingsPage(@PathVariable Long id, Model model) {
        model.addAttribute("ratings", ratingService.findAll());
        model.addAttribute("id", id);
        Book book = bookService.findByID(id).get();
        model.addAttribute("book", book);
        return "rateBook";
    }

    @PostMapping
    public String addRatingForBook(@RequestParam Long bookId,
                                   @RequestParam Long ratingId) {
        Book book = bookService.addRating(ratingId, bookId);
        return "redirect:/books";
    }
}
