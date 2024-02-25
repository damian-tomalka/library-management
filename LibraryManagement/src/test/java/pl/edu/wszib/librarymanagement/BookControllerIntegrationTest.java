package pl.edu.wszib.librarymanagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.edu.wszib.librarymanagement.controller.BookController;
import pl.edu.wszib.librarymanagement.service.BookService;
import pl.edu.wszib.librarymanagement.model.Book;

import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
@WebMvcTest(BookController.class)
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    @Test
    public void testGetAllBooksSuccess() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Author");
        book.setPublishYear(2021);
        book.setIsbn("1234567890");

        given(bookService.findAllBooks()).willReturn(List.of(book));

        mockMvc.perform(get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Book"));
    }

    @Test
    public void testGetBookByIdNotFound() throws Exception {
        Long bookId = 2L;

        given(bookService.findBookById(anyLong())).willReturn(Optional.empty());

        mockMvc.perform(get("/api/books/{id}", bookId)
                        .with(httpBasic("admin", "admin"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
