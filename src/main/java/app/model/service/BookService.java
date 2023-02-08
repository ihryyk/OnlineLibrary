package app.model.service;

import app.model.dao.exeption.ServiceException;
import app.model.entity.Book;

import java.util.List;

public interface BookService {
    public Book getById(int bookId, String language) throws ServiceException;
    public List<Book> getByAuthor(String author, String language, int start, int total) throws ServiceException;
    public int getCountBooksByTitle(String title) throws ServiceException;
    public int getCount() throws ServiceException;
    public int getCountBooksByAuthor(String author) throws ServiceException;
    public List<Book> getAll(String language, int start, int total) throws ServiceException;
    public List<Book> getAllForAdmin(int start, int total) throws ServiceException;
    public List<Book> getByTitle(String title, String language, int start, int total) throws ServiceException;
    public List<Book> sortBy(String request, String language, int start, int total) throws ServiceException;
    public void changeNumber(long bookId, int newNumber) throws ServiceException;
    public void update(Book updatedBook) throws ServiceException;
    public  void delete(long id) throws ServiceException;
    public void add(Book book) throws ServiceException;
}
