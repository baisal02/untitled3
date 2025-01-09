package org.example.BookDAO;

import org.example.Author;
import org.example.Book;

import java.util.List;
import java.util.Map;

public interface BookDao {

    public void createTableBook();

    public Book findBookById(Long id);

    public void updateBook(Book book,Long id);

    public void deleteBookById(Long id);

    public void dropTableBook();

}
