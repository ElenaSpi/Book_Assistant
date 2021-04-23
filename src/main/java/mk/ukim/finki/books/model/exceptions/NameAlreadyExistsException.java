package mk.ukim.finki.books.model.exceptions;

public class NameAlreadyExistsException extends RuntimeException {
    public NameAlreadyExistsException() {
        super("The name of the book already exists!");
    }
}
