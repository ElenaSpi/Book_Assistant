package mk.ukim.finki.books.model.exceptions;

public class AuthorException extends RuntimeException {
    public AuthorException() {
        super("Author must be provided for a book!");
    }
}