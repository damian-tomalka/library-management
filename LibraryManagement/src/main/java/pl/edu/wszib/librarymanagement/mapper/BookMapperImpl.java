package pl.edu.wszib.librarymanagement.mapper;

import org.springframework.stereotype.Component;
import pl.edu.wszib.librarymanagement.dto.BookDTO;
import pl.edu.wszib.librarymanagement.model.Book;

@Component
public class BookMapperImpl implements IBookMapper {

    @Override
    public BookDTO bookToBookDTO(Book book) {
        if (book == null) {
            return null;
        }

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPublishYear(book.getPublishYear());
        bookDTO.setIsbn(book.getIsbn());

        return bookDTO;
    }

    @Override
    public Book bookDTOToBook(BookDTO bookDTO) {
        if (bookDTO == null) {
            return null;
        }

        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublishYear(bookDTO.getPublishYear());
        book.setIsbn(bookDTO.getIsbn());

        return book;
    }
}