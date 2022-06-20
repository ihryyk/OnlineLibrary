package app.model.dao;

import app.model.dao.exeption.DAOException;
import app.model.entity.Book;
import app.model.entity.BookTranslation;

import java.sql.Connection;
import java.util.List;

/**
 * The interface defines methods for implementing different
 * activities with book
 *
 * @author Ihor Berezovskyi
 */
public interface BookDAO {
    /**
     * Returns information about book by id and language
     *
     * @param bookId   - Id of the book.
     * @param language - Language.
     * @return information about book.
     * @throws DAOException if there was an error executing the query
     *                      in the database
     * @see Book
     */
    public Book getById(long bookId, String language) throws DAOException;

    /**
     * Returns information about book by author and language
     *
     * @param author   - author of the book.
     * @param language - Language.
     * @param start    - position for retrieving data from a database
     * @param total    -  count of bookmarks displayed on one page
     * @return information about book.
     * @throws DAOException if there was an error executing the query
     *                      in the database
     * @see Book
     */
    public List<Book> getByAuthor(String author, String language, int start, int total) throws DAOException;

    /**
     * Returns total number books int database.
     *
     * @return total number books int database.
     * @throws DAOException if there was an error executing the query
     *                      in the database
     * @see Book
     */
    public int getCount() throws DAOException;

    /**
     * Returns information about book in database for specified language
     *
     * @param start    - position for retrieving data from a database
     * @param total    -  count of bookmarks displayed on one page
     * @param language - Language.
     * @return information about book.
     * @throws DAOException if there was an error executing the query
     *                      in the database
     * @see Book
     */
    public List<Book> getAll(String language, int start, int total) throws DAOException;

    /**
     * Returns information about book by author and language
     *
     * @param title    -title of the book.
     * @param language - Language.
     * @param start    - position for retrieving data from a database
     * @param total    -  count of bookmarks displayed on one page
     * @return information about book.
     * @throws DAOException if there was an error executing the query
     *                      in the database
     * @see Book
     */
    public List<Book> getByTitle(String title, String language, int start, int total) throws DAOException;

    /**
     * Returns sorted information about book for specified language
     *
     * @param language - Language.
     * @param start    - position for retrieving data from a database.
     * @param total    -  count of bookmarks displayed on one page.
     * @param request  -  database query for sorting data.
     * @return sorted information about book for specified language.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Book
     */
    public List<Book> sortBy(String request, String language, int start, int total) throws DAOException;

    /**
     * The function which changes the amount of books by book id
     *
     * @param bookId    - book of id.
     * @param newNumber - amount of books.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Book
     */
    public void changeNumber(long bookId, int newNumber) throws DAOException;

    /**
     * Returns total number books int database by author.
     *
     * @param author - author of the book.
     * @return total number books int database.
     * @throws DAOException if there was an error executing the query
     *                      in the database
     * @see Book
     */
    public int getCountBooksByAuthor(String author) throws DAOException;

    /**
     * Returns total number books int database by title.
     *
     * @param title - title of the book.
     * @return total number books int database.
     * @throws DAOException if there was an error executing the query
     *                      in the database
     * @see Book
     */
    public int getCountBooksByTitle(String title) throws DAOException;

    public List<BookTranslation> getTranslation(Connection connection, long bookId) throws DAOException;

    /**
     * Returns list of book(in two languages) from database
     *
     * @param start - position for retrieving data from a database
     * @param total -  count of bookmarks displayed on one page
     * @return information about book.
     * @throws DAOException if there was an error executing the query
     *                      in the database
     * @see Book
     */
    public List<Book> getAllForAdmin(int start, int total) throws DAOException;

    /**
     * The function which delete the book from database by book id.
     *
     * @param id - id of book.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Book
     */
    public void delete(long id) throws DAOException;

    /**
     * The function which add a new book to database.
     *
     * @param book - the book which will be added to database.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Book
     */
    public void add(Book book) throws DAOException;

    /**
     * The function which updates information about the book.
     *
     * @param updatedBook - a book with new information.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Book
     */
    public void update(Book updatedBook) throws DAOException;
}
