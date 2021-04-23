package mk.ukim.finki.books;

import mk.ukim.finki.books.model.Author;
import mk.ukim.finki.books.repository.AuthorRepository;
import mk.ukim.finki.books.service.AuthorService;
import mk.ukim.finki.books.service.implementation.AuthorServiceImplementation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SaveAuthorTest {

    @Mock
    private AuthorRepository authorRepository;
    private AuthorService service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Author author = new Author("name", "surname");
        Mockito.when(this.authorRepository.save(Mockito.any(Author.class))).thenReturn(author);
        this.service = Mockito.spy(new AuthorServiceImplementation(this.authorRepository));
    }

    @Test
    public void testSuccess() {
        Author author = this.service.save("name", "surname");
        Mockito.verify(this.service).save("name","surname");

        Assert.assertNotNull("Author is not null", author);
        Assert.assertEquals("Name does not match", "name", author.getName());
        Assert.assertEquals("Surname does not match", "surname", author.getSurname());
    }
}