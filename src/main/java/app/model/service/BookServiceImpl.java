package app.model.service;

import app.controller.servlets.util.validate.ArgumentValidator;
import app.model.dao.BookDAO;
import app.model.dao.DAOFactory;
import app.model.dao.exeption.DAOException;
import app.model.dao.exeption.ServiceException;
import app.model.entity.Book;
import org.apache.log4j.Logger;

import java.util.List;

public class BookServiceImpl implements BookService {
    Logger logger = Logger.getLogger(BookServiceImpl.class);
    private BookDAO bookDAO = DAOFactory.getBookDAO();

    /**
     * Returns information about book by id and language
     *
     * @param bookId   - id of the book.
     * @param language - Language.
     * @return information about book.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see Book
     */
    public Book getById(int bookId, String language) throws ServiceException {
        try {
            Book book = bookDAO.getById(bookId, language);
            logger.info("The result of the 'getById' function is obtained");
            return book;
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in getById at bookService class", e);
        }
    }

    /**
     * Returns information about book by author and language
     *
     * @param author   - author of the book.
     * @param language - Language.
     * @param start    - position for retrieving data from a database
     * @param total    -  count of bookmarks displayed on one page
     * @return information about book.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see Book
     */
    public List<Book> getByAuthor(String author, String language, int start, int total) throws ServiceException {
        try {
            List<Book> books = bookDAO.getByAuthor(author, language, start, total);
            logger.info("The result of the 'getByAuthor' function is obtained");
            return books;
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in getByAuthor at bookService class", e);
        }
    }

    /**
     * Returns total number books int database.
     *
     * @return total number books int database.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see Book
     */
    public int getCountBooksByTitle(String title) throws ServiceException {
        try {
            int countBooksByTitle = bookDAO.getCountBooksByTitle(title);
            logger.info("The result of the 'getCount' function is obtained");
            return countBooksByTitle;
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in getCountBooksByTitle at bookService class", e);
        }

    }

    /**
     * Returns total number books int database.
     *
     * @return total number books int database.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see Book
     */
    public int getCount() throws ServiceException {
        try {
            int count = bookDAO.getCount();
            logger.info("The result of the 'getCountBooksByTitle' function is obtained");
            return count;
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in getCount at bookService class", e);
        }
    }

    /**
     * Returns total number books int database by author.
     *
     * @param author - author of the book.
     * @return total number books int database.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see Book
     */
    public int getCountBooksByAuthor(String author) throws ServiceException {
        try {
            int countBooksByAuthor = bookDAO.getCountBooksByAuthor(author);
            logger.info("The result of the 'getCountBooksByAuthor' function is obtained");
            return countBooksByAuthor;
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in getCountBooksByAuthor at bookService class", e);
        }
    }

    /**
     * Returns information about book in database for specified language
     *
     * @param start    - position for retrieving data from a database
     * @param total    -  count of bookmarks displayed on one page
     * @param language - Language.
     * @return information about book.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see Book
     */
    public List<Book> getAll(String language, int start, int total) throws ServiceException {
        try {
            List<Book> books = bookDAO.getAll(language, start, total);
            logger.info("The result of the 'getAll' function is obtained");
            return books;
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in getAll at bookService class", e);
        }
    }

    /**
     * Returns list of book(in two languages) from database
     *
     * @param start - position for retrieving data from a database
     * @param total -  count of bookmarks displayed on one page
     * @return information about book.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see Book
     */
    public List<Book> getAllForAdmin(int start, int total) throws ServiceException {
        try {
            List<Book> books = bookDAO.getAllForAdmin(start, total);
            logger.info("The result of the 'getAllForAdmin' function is obtained");
            return books;
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in getAllForAdmin at bookService class", e);
        }
    }

    /**
     * Returns information about book by author and language
     *
     * @param title    -title of the book.
     * @param language - Language.
     * @param start    - position for retrieving data from a database
     * @param total    -  count of bookmarks displayed on one page
     * @return information about book.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see Book
     */
    public List<Book> getByTitle(String title, String language, int start, int total) throws ServiceException {
        try {
            List<Book> books = bookDAO.getByTitle(title, language, start, total);
            logger.info("The result of the 'getByTitle' function is obtained");
            return books;
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in getByTitle at bookService class", e);
        }
    }

    /**
     * Returns sorted information about book for specified language
     *
     * @param language - Language.
     * @param start    - position for retrieving data from a database.
     * @param total    -  count of bookmarks displayed on one page.
     * @param request  -  database query for sorting data.
     * @return sorted information about book for specified language.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see Book
     */
    public List<Book> sortBy(String request, String language, int start, int total) throws ServiceException {
        try {
            List<Book> books = bookDAO.sortBy(request, language, start, total);
            logger.info("The result of the 'sortBy' function is obtained");
            return books;
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in sortBy at bookService class", e);
        }
    }

    /**
     * The function which changes the amount of books by book id
     *
     * @param bookId    - book of id.
     * @param newNumber - amount of books.
     * @throws ServiceException if there was an error executing the query in the service
     * @see Book
     */
    public synchronized void changeNumber(long bookId, int newNumber) throws ServiceException {
        try {
            bookDAO.changeNumber(bookId, newNumber);
            logger.info("The 'changeNumber' function has been completed");
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in changeNumber at bookService class", e);
        }
    }

    /**
     * The function which updates information about the book.
     *
     * @param updatedBook - a book with new information.
     * @throws ServiceException if there was an error executing the query in the service
     * @see Book
     */
    public synchronized void update(Book updatedBook) throws ServiceException {
        ArgumentValidator.checkForNull(updatedBook, "Not allow for a null book in update at bookService class");
        try {
            bookDAO.update(updatedBook);
            logger.info("The 'update' function has been completed");
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in update at bookService class", e);
        }
    }

    /**
     * The function which delete the book from database by book id.
     *
     * @param id - id of book.
     * @throws ServiceException if there was an error executing the query in the service
     * @see Book
     */
    public synchronized void delete(long id) throws ServiceException {
        try {
            bookDAO.delete(id);
            logger.info("The 'delete' function has been completed");
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in delete at bookService class", e);
        }
    }

    /**
     * The function which add a new book to database.
     *
     * @param book - the book which will be added to database.
     * @throws ServiceException if there was an error executing the query in the service
     * @see Book
     */
    public void add(Book book) throws ServiceException {
        ArgumentValidator.checkForNull(book, "Not allow for a null book in add at bookService class");
        try {
            bookDAO.add(book);
            logger.info("The 'add' function has been completed");
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in add at bookService class", e);
        }
    }
}
