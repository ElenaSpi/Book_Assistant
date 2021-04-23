package mk.ukim.finki.books.web;

import mk.ukim.finki.books.service.AuthorService;
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
@RequestMapping("/createAuthor")
public class CreateAuthorController {
    private final AuthorService authorService;
    private final SpringTemplateEngine springTemplateEngine;

    public CreateAuthorController(AuthorService authorService, SpringTemplateEngine springTemplateEngine) {
        this.authorService = authorService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @GetMapping
    public String addAuthor() throws ServletException, IOException {
        return "addAuthor";
    }

    @PostMapping
    public String createNewAuthor(@RequestParam String name, @RequestParam String surname){
        authorService.save(name,surname);
        return "redirect:/authors";
    }
}