package pl.edu.wszib.librarymanagement.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.edu.wszib.librarymanagement.dto.BookDTO;
import pl.edu.wszib.librarymanagement.model.Book;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-25T11:09:35+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.9 (JetBrains s.r.o.)"
)
@Component
public class IBookMapperImpl implements IBookMapper {

    @Override
    public BookDTO bookToBookDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDTO bookDTO = new BookDTO();

        bookDTO.setId( book.getId() );
        bookDTO.setTitle( book.getTitle() );
        bookDTO.setAuthor( book.getAuthor() );
        bookDTO.setPublishYear( book.getPublishYear() );
        bookDTO.setIsbn( book.getIsbn() );

        return bookDTO;
    }

    @Override
    public Book bookDTOToBook(BookDTO bookDTO) {
        if ( bookDTO == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( bookDTO.getId() );
        book.setTitle( bookDTO.getTitle() );
        book.setAuthor( bookDTO.getAuthor() );
        book.setPublishYear( bookDTO.getPublishYear() );
        book.setIsbn( bookDTO.getIsbn() );

        return book;
    }
}
