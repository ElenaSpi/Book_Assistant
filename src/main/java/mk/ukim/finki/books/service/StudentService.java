package mk.ukim.finki.books.service;

import mk.ukim.finki.books.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> listAll();
    List<Student> searchByNameOrSurname(String text);
    Student save(String username, String password, String name, String surname);
    Student findStudent(String username);
}