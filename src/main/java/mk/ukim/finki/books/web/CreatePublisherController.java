package mk.ukim.finki.books.web;

import mk.ukim.finki.books.model.Publisher;
import mk.ukim.finki.books.service.AuthorService;
import mk.ukim.finki.books.service.PublisherService;
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
@RequestMapping("/createPublisher")
public class CreatePublisherController {
    private final PublisherService publisherService;
    private final SpringTemplateEngine springTemplateEngine;

    public CreatePublisherController(PublisherService publisherService, SpringTemplateEngine springTemplateEngine) {
        this.publisherService = publisherService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @GetMapping
    public String addPublisher() throws ServletException, IOException {
        return "addNewPublisher";
    }

    @PostMapping
    public String createNewPublisher(@RequestParam String name){
        publisherService.save(name);
        return "redirect:/publishers";
    }
}