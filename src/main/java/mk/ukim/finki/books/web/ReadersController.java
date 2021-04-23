package mk.ukim.finki.books.web;

import mk.ukim.finki.books.model.Book;
import mk.ukim.finki.books.model.Student;
import mk.ukim.finki.books.service.BookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/readers")
public class ReadersController {
    private final BookService bookService;

    public ReadersController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getReadersPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("books", this.bookService.listBooks());
        return "bookReaders";
    }
}