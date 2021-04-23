package mk.ukim.finki.books.model.exceptions;

public class IllegalArgumentsException extends RuntimeException {
    public IllegalArgumentsException() {
        super("Please provide the necessary arguments");
    }
}
