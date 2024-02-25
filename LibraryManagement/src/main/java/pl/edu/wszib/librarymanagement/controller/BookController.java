package pl.edu.wszib.librarymanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.edu.wszib.librarymanagement.dto.BookDTO;
import pl.edu.wszib.librarymanagement.mapper.IBookMapper;
import pl.edu.wszib.librarymanagement.model.Book;
import pl.edu.wszib.librarymanagement.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Qualifier("IBookMapperImpl")
    @Autowired
    private IBookMapper IBookMapper;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.findBookById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        Book book = IBookMapper.bookDTOToBook(bookDTO);
        book = bookService.saveBook(book);
        return ResponseEntity.ok(IBookMapper.bookToBookDTO(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Optional<Book> bookOptional = bookService.findBookById(id);

        if (bookOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Book book = bookOptional.get();
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setPublishYear(bookDetails.getPublishYear());
        book.setIsbn(bookDetails.getIsbn());

        Book updatedBook = bookService.saveBook(book);
        return ResponseEntity.ok(updatedBook);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> updateBookPartially(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Optional<Book> bookOptional = bookService.findBookById(id);

        if (bookOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Book book = bookOptional.get();
        if (updates.containsKey("title")) {
            book.setTitle((String) updates.get("title"));
        }
        if (updates.containsKey("author")) {
            book.setAuthor((String) updates.get("author"));
        }
        if (updates.containsKey("publishYear")) {
            book.setPublishYear((Integer) updates.get("publishYear"));
        }
        if (updates.containsKey("isbn")) {
            book.setIsbn((String) updates.get("isbn"));
        }

        Book updatedBook = bookService.saveBook(book);
        return ResponseEntity.ok(updatedBook);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.findBookById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
