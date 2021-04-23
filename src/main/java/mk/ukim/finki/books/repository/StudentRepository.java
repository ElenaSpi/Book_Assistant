package mk.ukim.finki.books.repository;

import mk.ukim.finki.books.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUsername(String username);
    List<Student> findByName(String name);
    List<Student> findAllByNameLikeOrSurnameLike(String name, String surname);
    Student findAllByUsernameAndPassword(String username, String password);
}