package mk.ukim.finki.books.model.exceptions;

public class PublisherAlreadyExists extends RuntimeException {
    public PublisherAlreadyExists() {
        super("Publisher already exists!");
    }
}
