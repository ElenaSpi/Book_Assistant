package mk.ukim.finki.books.web;

import mk.ukim.finki.books.service.RatingService;
import mk.ukim.finki.books.service.StudentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import java.io.IOException;

@Controller
@RequestMapping("/createRating")
public class CreateRatingController {
    private final RatingService ratingService;
    private final SpringTemplateEngine springTemplateEngine;

    public CreateRatingController(RatingService ratingService, SpringTemplateEngine springTemplateEngine) {
        this.ratingService = ratingService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addRating() throws ServletException, IOException {
        return "addNewRating";
    }

    @PostMapping
    public String createNewRating(@RequestParam String message, @RequestParam Integer number) {
        ratingService.save(message, number);
        return "redirect:/books";
    }
}