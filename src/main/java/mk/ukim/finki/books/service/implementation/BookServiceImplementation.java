package mk.ukim.finki.books.service.implementation;

import mk.ukim.finki.books.model.*;
import mk.ukim.finki.books.model.enumerations.Type;
import mk.ukim.finki.books.model.exceptions.*;
import mk.ukim.finki.books.repository.BookRepository;
import mk.ukim.finki.books.repository.RatingRepository;
import mk.ukim.finki.books.repository.StudentRepository;
import mk.ukim.finki.books.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplementation implements BookService {
    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;
    private final RatingRepository ratingRepository;

    public BookServiceImplementation(StudentRepository studentRepository, BookRepository bookRepository, RatingRepository ratingRepository) {
        this.studentRepository = studentRepository;
        this.bookRepository = bookRepository;
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<Book> listBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findByID(Long id) {
        return this.bookRepository.findByID(id);
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        return this.bookRepository.findByAuthor(author);
    }

    @Override
    public Book edit(Long id, String name, Integer number, Author author,
                     Publisher publisher, Type type) {
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            throw new BookDoesNotExist();
        }
        else if(!book.get().getName().equals(name)){
            Optional<Book> nameAlreadyExists = bookRepository.findAllByName(name);
            if (nameAlreadyExists.isPresent()){
                throw new NameAlreadyExistsException();
            }
        }
        this.bookRepository.deleteByID(book.get().getID());
        Book newBook = new Book(name, number, author, publisher, type);
        this.bookRepository.save(newBook);
        return newBook;
    }


    @Override
    public Book save(String name, Integer numPages, Author author, Publisher publisher, Type type) {
        if (name == null || name.isEmpty()){
            throw  new IllegalArgumentsException();
        } else if (numPages == null){
            throw  new IllegalArgumentsException();
        }
        Optional<Book> book = bookRepository.findAllByName(name);
        if (book.isPresent()) {
            throw new NameAlreadyExistsException();
        }
        Book book2 = new Book(name, numPages, author, publisher, type);
        return this.bookRepository.save(book2);
    }

    @Override
    public void deleteByID(Long id) {
        this.bookRepository.deleteByID(id);
    }

    @Override
    public Book addReader(String username, Long bookID) {
        Book book = this.bookRepository.findByID(bookID).get();
        Student student = this.studentRepository.findByUsername(username);
        if(book == null || student == null) {
            throw new BookDoesNotExist();
        }
        if (book.getStudents().contains(student)) return book;
        book.getStudents().add(student);
        return this.bookRepository.save(book);
    }

    @Override
    public Book addRating(Long ratingID, Long bookID) {
        Book book = this.bookRepository.findByID(bookID).get();
        Rating rating = this.ratingRepository.findById(ratingID).get();
        if(book.getRatings().contains(rating)) return book;
        book.getRatings().add(rating);
        return this.bookRepository.save(book);
    }

    @Override
    public List<Student> listReadersByBook(Long bookID) {
        if(!this.bookRepository.findById(bookID).isPresent()) {
            throw new BookDoesNotExist();
        }
        List<Student> students = this.bookRepository.findByID(bookID).get().getStudents();
        return students;
    }

    @Override
    public List<Book> findByPublisher(Publisher publisher) {
        return this.bookRepository.findByPublisher(publisher);
    }
}

