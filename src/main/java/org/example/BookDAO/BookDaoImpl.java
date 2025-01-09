package org.example.BookDAO;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import org.example.Author;
import org.example.Book;
import org.example.DatabaseConfig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class BookDaoImpl implements BookDao {
    @Override
    public void createTableBook() {
        String str = "create table if not exists books(" +
                "id bigint primary key," +
                "name varchar," +
                "published int," +
                "price int," +
                "author_id bigint" +
                "foreign key(author_id) references authors(id))";
        try (Statement statement = DatabaseConfig.getConnection().createStatement()) {
            statement.executeUpdate(str);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Book findBookById(Long id) {
        String str = "select * from books where id=?";
        Book book = new Book();
        try (PreparedStatement preparedStatement = DatabaseConfig.getConnection().prepareStatement(str)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                book.setId(resultSet.getLong("id"));
                book.setName(resultSet.getString("name"));
                book.setPublished(resultSet.getInt("published"));
                book.setPrice(resultSet.getInt("price"));
                book.setAuthorId(resultSet.getLong("author_id"));
            }
            return book;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateBook(Book book, Long id) {
        String str = "update books set name=?,published=?,price=?,author_id=? where id=?";
        try (PreparedStatement preparedStatement = DatabaseConfig.getConnection().prepareStatement(str)) {
            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getPublished());
            preparedStatement.setInt(3, book.getPrice());
            preparedStatement.setLong(4, book.getAuthorId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteBookById(Long id) {
        String str = "delete from books where id=?";
        try (PreparedStatement preparedStatement = DatabaseConfig.getConnection().prepareStatement(str)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropTableBook() {
        String str = "drop table if exists books";
        try(Statement statement = DatabaseConfig.getConnection().createStatement()){
            statement.executeUpdate(str);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
