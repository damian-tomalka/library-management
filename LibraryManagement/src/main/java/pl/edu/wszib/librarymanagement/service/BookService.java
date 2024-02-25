package pl.edu.wszib.librarymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.edu.wszib.librarymanagement.dao.BookDao;
import pl.edu.wszib.librarymanagement.model.Book;
import pl.edu.wszib.librarymanagement.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookDao bookDao;
    @Autowired
    public BookService(Optional<BookRepository> bookRepository, Optional<BookDao> bookDao) {
        this.bookRepository = bookRepository.orElse(null);
        this.bookDao = bookDao.orElse(null);
    }

    public List<Book> findAllBooks() {
        return bookRepository != null ? bookRepository.findAll() : bookDao.findAll();
    }

    public Optional<Book> findBookById(Long id) {
        return bookRepository != null ? bookRepository.findById(id) : Optional.ofNullable(bookDao.findById(id));
    }

    public Book saveBook(Book book) {
        return bookRepository != null ? bookRepository.save(book) : bookDao.save(book);
    }

    public void deleteBook(Long id) {
        if (bookRepository != null) {
            bookRepository.deleteById(id);
        } else {
            bookDao.deleteById(id);
        }
    }
}
