package mk.ukim.finki.books.service.implementation;

import mk.ukim.finki.books.model.Student;
import mk.ukim.finki.books.model.exceptions.IllegalArgumentsException;
import mk.ukim.finki.books.model.exceptions.StudentAlreadyExistsException;
import mk.ukim.finki.books.repository.StudentRepository;
import mk.ukim.finki.books.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImplementation implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImplementation(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        return studentRepository.findAllByNameLikeOrSurnameLike(text, text);
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        if( username.isEmpty() || password.isEmpty() || name.isEmpty() || surname.isEmpty()) {
            throw new IllegalArgumentsException();
        }

        Student student = studentRepository.findByUsername(username);
        if (student != null){
            throw  new StudentAlreadyExistsException();
        }

        return studentRepository.save(new Student(username, password, name, surname));
    }

    @Override
    public Student findStudent(String username) {
        return studentRepository.findByUsername(username);
    }
}