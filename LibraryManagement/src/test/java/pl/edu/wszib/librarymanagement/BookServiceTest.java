package pl.edu.wszib.librarymanagement.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.wszib.librarymanagement.model.Book;
import pl.edu.wszib.librarymanagement.repository.BookRepository;

import java.util.Optional;

@SpringBootTest
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testFindBookByIdSuccess() {
        Long bookId = 1L;
        Book mockBook = new Book();
        mockBook.setId(bookId);
        mockBook.setTitle("Test Book");
        mockBook.setAuthor("Author");
        mockBook.setPublishYear(2021);
        mockBook.setIsbn("1234567890");

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(mockBook));

        Optional<Book> foundBook = bookService.findBookById(bookId);

        assertTrue(foundBook.isPresent(), "Book should be found");
        assertEquals(foundBook.get().getTitle(), "Test Book", "Book title should match");
    }

    @Test
    public void testFindBookByIdNotFound() {
        Long bookId = 2L;

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        Optional<Book> foundBook = bookService.findBookById(bookId);

        assertFalse(foundBook.isPresent(), "Book should not be found");
    }

}