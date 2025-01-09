package org.example;

import org.example.dao.daoImpl.AuthorDao;
import org.example.dao.daoImpl.AuthorImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AuthorDao authorDao = new AuthorImpl();
        authorDao.createAuthorTable();
    }
}
