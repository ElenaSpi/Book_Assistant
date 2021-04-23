package mk.ukim.finki.books.model.exceptions;

public class IllegalBookNameException extends RuntimeException {
    public IllegalBookNameException() {
        super("Book name must be written!");
    }
}
