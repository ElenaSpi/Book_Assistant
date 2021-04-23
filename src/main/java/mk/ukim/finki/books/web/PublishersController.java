package mk.ukim.finki.books.web;

import mk.ukim.finki.books.service.PublisherService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/publishers")
public class PublishersController {
    private final PublisherService publisherService;

    public PublishersController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public String getReadersPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("publishers", this.publisherService.findAll());
        return "publishers";
    }
}
