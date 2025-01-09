package org.example.dao.daoImpl;

import org.example.Author;
import org.example.DatabaseConfig;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AuthorImpl implements AuthorDao {
    @Override
    public boolean createAuthorTable() {
        String str = "create table if not exists authors(" +
                "id serial primary key," +
                "first_name varchar," +
                "last_name varchar," +
                "email varchar," +
                "country varchar," +
                "date_of_birth date)";

        try(Statement statement = DatabaseConfig.getConnection().createStatement()){
            statement.executeUpdate(str);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public String saveAuthor(Author author) {
        String str = "insert into authors (first_name,last_name,email,country,date_of_birth) values (?,?,?,?,?)";
        try(PreparedStatement preparedStatement = DatabaseConfig.getConnection().prepareStatement(str)){
            preparedStatement.setString(1,author.getFirstname());
            preparedStatement.setString(2,author.getLastname());
            preparedStatement.setString(3,author.getEmail());
            preparedStatement.setString(4,author.getCountry());
            preparedStatement.setDate(5, Date.valueOf(author.getDateOfBirth()));
            preparedStatement.executeUpdate();
            return "successfully saved";
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return "did not get savedl";
    }

    @Override
    public String updateAuthor(Author author, Long authorid) {
        String str = "update authors set first_name=?,last_name=?,email=?,country=?,date_of_birth=? where id=?";
        try(PreparedStatement preparedStatement = DatabaseConfig.getConnection().prepareStatement(str)){
            preparedStatement.setString(1,author.getFirstname());
            preparedStatement.setString(2,author.getLastname());
            preparedStatement.setString(3,author.getEmail());
            preparedStatement.setString(4,author.getCountry());
            preparedStatement.setDate(5,Date.valueOf(author.getDateOfBirth()));
            preparedStatement.setLong(6,authorid);
            preparedStatement.executeUpdate();
            return "successfully updated";
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return "not updated";
    }

    @Override
    public Author getAuthor(Long authorid) {
        String str = "select * from authors where id =?";
        Author author = new Author();
        try(PreparedStatement preparedStatement = DatabaseConfig.getConnection().prepareStatement(str)){
            preparedStatement.setLong(1, authorid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                author.setId(resultSet.getLong("id"));
            author.setFirstname(resultSet.getString("first_name"));
            author.setLastname(resultSet.getString("last_name"));
            author.setEmail(resultSet.getString("email"));
            author.setCountry(resultSet.getString("country"));
            author.setDateOfBirth(LocalDate.parse(resultSet.getString("date_of_birth")));
            }
            return author;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Author> getAuthors() {
        String query = "select * from authors";
        try (Statement statement = DatabaseConfig.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            List<Author> authors = new ArrayList<>();
            while (resultSet.next()) {
                authors.add(new Author(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("country"),
                        resultSet.getDate("date_of_birth").toLocalDate()
                ));
            }
            return authors;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteAuthor(Long authorid) {
        String str = "delete from authors where id=?";
        try(PreparedStatement preparedStatement = DatabaseConfig.getConnection().prepareStatement(str)){
            preparedStatement.setLong(1, authorid);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropTable() {
        String str = "drop table if exists authors";
        try(Statement statement = DatabaseConfig.getConnection().createStatement()){
            statement.executeUpdate(str);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean clearTable() {
        String str = "truncate table authors";
        try(Statement statement= DatabaseConfig.getConnection().createStatement()){
            statement.executeUpdate(str);
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Author> sortByDateOfBirth() {
        String str = "select * from authors order by date_of_birth asc";
        List<Author> authors = new ArrayList<>();
        try(Statement statement = DatabaseConfig.getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery(str);
            while (resultSet.next()) {
                authors.add(new Author(
                                resultSet.getLong("id"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name"),
                                resultSet.getString("email"),
                                resultSet.getString("country"),
                                resultSet.getDate("date_of_birth").toLocalDate()
                ));
            }
            return authors;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
