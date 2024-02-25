package pl.edu.wszib.librarymanagement.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.librarymanagement.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Profile("list")
public class ListBookDao implements BookDao {

    private final List<Book> books = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books);
    }

    @Override
    public Book findById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Book save(Book book) {
        book.setId(counter.incrementAndGet());
        books.add(book);
        return book;
    }

    @Override
    public void deleteById(Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }
}
