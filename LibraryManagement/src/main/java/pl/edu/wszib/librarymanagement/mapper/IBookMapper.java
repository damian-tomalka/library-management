package pl.edu.wszib.librarymanagement.mapper;

import org.mapstruct.Mapper;
import pl.edu.wszib.librarymanagement.dto.BookDTO;
import pl.edu.wszib.librarymanagement.model.Book;

@Mapper(componentModel = "spring")
public interface IBookMapper {

    BookDTO bookToBookDTO(Book book);
    Book bookDTOToBook(BookDTO bookDTO);
}