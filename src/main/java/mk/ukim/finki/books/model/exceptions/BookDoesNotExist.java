package mk.ukim.finki.books.model.exceptions;

public class BookDoesNotExist extends RuntimeException {
    public BookDoesNotExist() {
        super("The book does not exist!");
    }
}
