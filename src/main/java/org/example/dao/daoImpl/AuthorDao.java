package org.example.dao.daoImpl;

import org.example.Author;

import java.util.List;

public interface AuthorDao {
    boolean createAuthorTable();
    String saveAuthor(Author author);
    String updateAuthor(Author author,Long authorid);
    Author getAuthor(Long authorid);
    List<Author> getAuthors();
    void deleteAuthor(Long authorid);
    void dropTable();
    boolean clearTable();
    List<Author> sortByDateOfBirth();
}
