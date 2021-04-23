package mk.ukim.finki.books.web;

import mk.ukim.finki.books.model.Book;
import mk.ukim.finki.books.service.BookService;
import mk.ukim.finki.books.service.StudentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Controller
@RequestMapping("/addStudents")
public class StudentsController {

    private final SpringTemplateEngine springTemplateEngine;
    private final StudentService studentService;
    private final BookService bookService;

    public StudentsController(SpringTemplateEngine springTemplateEngine, StudentService studentService, BookService bookService) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddStudentsPage(@PathVariable Long id, Model model) {
        model.addAttribute("students", studentService.listAll());
        model.addAttribute("id", id);
        Book book = bookService.findByID(id).get();
        model.addAttribute("book", book);
        return "listStudents";
    }

    @PostMapping
    public String enrollStudentAsReader(@RequestParam Long bookId, @RequestParam String studentId) {
        Book book = bookService.addReader(studentId, bookId);
        return  "redirect:/books";
    }
}