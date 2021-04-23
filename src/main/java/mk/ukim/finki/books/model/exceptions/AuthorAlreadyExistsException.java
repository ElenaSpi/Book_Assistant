package mk.ukim.finki.books.model.exceptions;

public class AuthorAlreadyExistsException extends RuntimeException {
    public AuthorAlreadyExistsException() {
        super("Author already exists!");
    }
}
