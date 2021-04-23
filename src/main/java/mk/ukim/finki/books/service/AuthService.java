package mk.ukim.finki.books.service;

import mk.ukim.finki.books.model.Student;

public interface AuthService {
    Student login(String username, String password);
}
