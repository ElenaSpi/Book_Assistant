package mk.ukim.finki.books.web;

import mk.ukim.finki.books.model.Author;
import mk.ukim.finki.books.model.Book;
import mk.ukim.finki.books.model.Publisher;
import mk.ukim.finki.books.model.enumerations.Rating;
import mk.ukim.finki.books.model.enumerations.Type;
import mk.ukim.finki.books.service.AuthorService;
import mk.ukim.finki.books.service.BookService;
import mk.ukim.finki.books.service.PublisherService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/","/books"})
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final PublisherService publisherService;

    public BookController(BookService bookService, AuthorService authorService, PublisherService publisherService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("books", this.bookService.listBooks());
        return "listBooks";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addNewBook(Model model) {
        model.addAttribute("authors", this.authorService.findAll());
        model.addAttribute("publishers", this.publisherService.findAll());
        return "addNewBook";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String name, @RequestParam Integer num,
                           @RequestParam Long author,
                           @RequestParam Long publisher,
                           @RequestParam Type type) {
        Author _author = this.authorService.findAuthorByID(author).get();
        Publisher _publisher = this.publisherService.findAllById(publisher).get();
        this.bookService.save(name, num, _author, _publisher, type);
        return "redirect:/books";
    }

    @PostMapping("/edit")
    public String editBook(@RequestParam Long bookID, @RequestParam String name, @RequestParam Integer num,
                           @RequestParam Long author,
                           @RequestParam Long publisher,
                           @RequestParam Type type) {
        Author _author = this.authorService.findAuthorByID(author).get();
        Publisher _publisher = this.publisherService.findAllById(publisher).get();
        this.bookService.edit(bookID, name, num, _author, _publisher, type);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editBook(@PathVariable Long id, Model model) {
        if(this.bookService.findByID(id).isPresent()) {
            Book book = this.bookService.findByID(id).get();
            model.addAttribute("book", book);
            model.addAttribute("authors", this.authorService.findAll());
            model.addAttribute("publishers", this.publisherService.findAll());
            return "addNewBook";
        }
        return "redirect:/books";
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteBook(@PathVariable Long id) {
        this.bookService.deleteByID(id);
        return "redirect:/books";
    }

    @GetMapping("/access_denied")
    public String accessDeniedPage(){
        return "access_denied";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String _deleteBook(@PathVariable Long id) {
        this.bookService.deleteByID(id);
        return "redirect:/books";
    }
}