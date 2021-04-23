package mk.ukim.finki.books.web;

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
@RequestMapping("/createStudent")
public class CreateStudentController {
    private final StudentService studentService;
    private final SpringTemplateEngine springTemplateEngine;

    public CreateStudentController(StudentService studentService, SpringTemplateEngine springTemplateEngine) {
        this.studentService = studentService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addStudent() throws ServletException, IOException {
        return "addNewStudent";
    }

    @PostMapping
    public String createNewStudent(@RequestParam String username, @RequestParam String password, @RequestParam String name, @RequestParam String surname){
        studentService.save(username,password,name,surname);
        return "redirect:/books";
    }
}