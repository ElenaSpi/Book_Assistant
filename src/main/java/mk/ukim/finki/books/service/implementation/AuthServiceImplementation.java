package mk.ukim.finki.books.service.implementation;

import mk.ukim.finki.books.model.Student;
import mk.ukim.finki.books.repository.StudentRepository;
import mk.ukim.finki.books.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImplementation implements AuthService {

    private final StudentRepository studentRepository;

    public AuthServiceImplementation(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            return null;
        }
        return studentRepository.findAllByUsernameAndPassword(username, password);
    }
}
