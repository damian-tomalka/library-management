package pl.edu.wszib.librarymanagement.dao;

import pl.edu.wszib.librarymanagement.model.Book;

import java.util.List;

public interface BookDao {
    List<Book> findAll();
    Book findById(Long id);
    Book save(Book book);
    void deleteById(Long id);
}
