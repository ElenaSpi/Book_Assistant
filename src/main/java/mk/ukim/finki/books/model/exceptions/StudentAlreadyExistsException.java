package mk.ukim.finki.books.model.exceptions;

public class StudentAlreadyExistsException extends RuntimeException {
    public StudentAlreadyExistsException() {
        super("The student already exists!");
    }
}