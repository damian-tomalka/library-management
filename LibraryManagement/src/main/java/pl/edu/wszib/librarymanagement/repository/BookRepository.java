package pl.edu.wszib.librarymanagement.repository;

import pl.edu.wszib.librarymanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
