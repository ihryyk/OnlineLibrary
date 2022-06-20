package app.model.dao.impl;

import app.model.dao.BookDAO;
import app.model.dao.exeption.DAOException;
import app.model.dao.util.BookUtil;
import app.model.dao.util.ConnectionPool;
import app.model.dao.util.ConnectionUtil;
import app.model.entity.Book;
import app.model.entity.BookTranslation;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static app.model.dao.util.SqlConstant.*;
import static app.model.dao.util.SqlConstant.BookRequestQuery.*;

/**
 * Implement an interface that defines different activities with book in database.
 *
 * @author Ihor Berezovskyi
 */
public class BookDAOImpl implements BookDAO {
    Logger logger = Logger.getLogger(BookDAOImpl.class);

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
    @Override
    public Book getById(long bookId, String language) throws DAOException {
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(SELECT_BOOK)) {
            pr.setLong(1, bookId);
            pr.setString(2, language);
            resultSet = pr.executeQuery();
            Book book = null;
            if (resultSet.next()) {
                book = BookUtil.bookInitialization(resultSet);
            }
            logger.info("The result of the 'getById' function is obtained");
            return book;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        } finally {
            ConnectionUtil.closeResultSet(resultSet);
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
     * @throws DAOException if there was an error executing the query
     *                      in the database
     * @see Book
     */
    @Override
    public List<Book> getByAuthor(String author, String language, int start, int total) throws DAOException {
        List<Book> books = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(GET_ALL_BOOKS_BY_AUTHOR + start + "," + total)) {
            pr.setString(1, author);
            pr.setString(2, language);
            resultSet = pr.executeQuery();
            while (resultSet.next()) {
                books.add(BookUtil.bookInitialization(resultSet));
            }
            logger.info("The result of the 'getByAuthor' function is obtained");
            return books;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        } finally {
            ConnectionUtil.closeResultSet(resultSet);
        }
    }

    /**
     * Returns total number books int database.
     *
     * @return total number books int database.
     * @throws DAOException if there was an error executing the query
     *                      in the database
     * @see Book
     */
    @Override
    public int getCount() throws DAOException {
        ResultSet rs = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement("SELECT count(*) FROM book WHERE deleted='0'")) {
            rs = pr.executeQuery();
            if (rs.next()) {
                logger.info("The result of the 'getCount' function is obtained");
                return rs.getInt(1);
            } else{
                logger.info("The result of the 'getCount' function is not obtained");
                return 0;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        }
    }

    /**
     * Returns total number books int database by title.
     *
     * @param title - title of the book.
     * @return total number books int database.
     * @throws DAOException if there was an error executing the query
     *                      in the database
     * @see Book
     */
    @Override
    public int getCountBooksByTitle(String title) throws DAOException {
        ResultSet rs = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(COUNT_BOOK_BY_TITLE)) {
            pr.setString(1, title);
            rs = pr.executeQuery();
            if (rs.next()) {
                logger.info("The result of the 'getCountBooksByTitle' function is obtained");
                return rs.getInt(1);
            } else return 0;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        }
    }

    /**
     * Returns total number books int database by author.
     *
     * @param author - author of the book.
     * @return total number books int database.
     * @throws DAOException if there was an error executing the query
     *                      in the database
     * @see Book
     */
    public int getCountBooksByAuthor(String author) throws DAOException {
        ResultSet rs = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_BOOK_BY_AUTHOR)) {
            preparedStatement.setString(1, author);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                logger.info("The result of the 'getCountBooksByAuthor' function is obtained");
                return rs.getInt(1);
            } else return 0;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        } finally {
            ConnectionUtil.closeResultSet(rs);
        }
    }

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
    @Override
    public List<Book> getAll(String language, int start, int total) throws DAOException {
        List<Book> books = new ArrayList<>();
        ResultSet rs = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(GET_ALL_BOOKS + start + "," + total)) {
            pr.setString(1, language);
            rs = pr.executeQuery();
            while (rs.next()) {
                books.add(BookUtil.bookInitialization(rs));
            }
            logger.info("The result of the 'getAll' function is obtained");
            return books;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        } finally {
            ConnectionUtil.closeResultSet(rs);
        }
    }

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
    public List<Book> getAllForAdmin(int start, int total) throws DAOException {
        List<Book> books = new ArrayList<>();

        ResultSet rs = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(GET_ALL_BOOKS_ADMIN + start + "," + total);
        ) {
            rs = pr.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt(BOOK_ID));
                book.setYear(rs.getInt(BOOK_YEAR));
                book.setPublishingHouse(rs.getString(PUBLISHING_HOUSE));
                book.setNumber(rs.getInt(BOOK_NUMBER));
                book.setBookTranslations(getTranslation(connection, book.getId()));
                books.add(book);
            }
            logger.info("The result of the 'getAllForAdmin' function is obtained");
            return books;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        } finally {
            ConnectionUtil.closeResultSet(rs);
        }
    }

    /**
     * Returns the translated information about a book int two by book id.
     *
     * @param connection - connection to database.
     * @param bookId     - id of a book.
     * @return information the translated information about a book int two by book id.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Book
     */
    public List<BookTranslation> getTranslation(Connection connection, long bookId) throws DAOException {
        ResultSet rs = null;
        List<BookTranslation> bookTranslationList = new ArrayList<>();
        try (PreparedStatement pr = connection.prepareStatement("Select * from translation_book INNER JOIN language l on translation_book.id_language = l.language_id WHERE id_book  = ?")) {
            pr.setLong(1, bookId);
            rs = pr.executeQuery();
            while (rs.next()) {
                bookTranslationList.add(BookUtil.bookTranslationInitialization(rs));
            }
            logger.info("The result of the 'getTranslation' function is obtained");
            return bookTranslationList;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        } finally {
            ConnectionUtil.closeResultSet(rs);
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
     * @throws DAOException if there was an error executing the query
     *                      in the database
     * @see Book
     */
    @Override
    public List<Book> getByTitle(String title, String language, int start, int total) throws DAOException {
        List<Book> books = new ArrayList<>();
        Book book = null;
        ResultSet rs = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(GET_ALL_BOOKS_BY_TITLE + start + "," + total)) {
            pr.setString(1, title);
            pr.setString(2, language);
            rs = pr.executeQuery();
            while (rs.next()) {
                books.add(BookUtil.bookInitialization(rs));
            }
            logger.info("The result of the 'getByTitle' function is obtained");
            return books;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        } finally {
            ConnectionUtil.closeResultSet(rs);
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
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Book
     */
    @Override
    public List<Book> sortBy(String request, String language, int start, int total) throws DAOException {
        List<Book> books = null;
        Book book = null;
        ResultSet rs = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(request + start + "," + total);) {
            books = new ArrayList<>();
            pr.setString(1, language);
            rs = pr.executeQuery();
            while (rs.next()) {
                books.add(BookUtil.bookInitialization(rs));
            }
            logger.info("The result of the 'sortBy' function is obtained");
            return books;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        } finally {
            ConnectionUtil.closeResultSet(rs);
        }
    }

    /**
     * The function which changes the amount of books by book id
     *
     * @param bookId    - book of id.
     * @param newNumber - amount of books.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Book
     */
    @Override
    public void changeNumber(long bookId, int newNumber) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(UPDATE_NUMBER_IN_BOOK)) {
            pr.setInt(1, newNumber);
            pr.setLong(2, bookId);
            pr.executeUpdate();
            logger.info("The 'changeNumber' function has been completed");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        }
    }

    /**
     * The function which updates information about the book.
     *
     * @param updatedBook - a book with new information.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Book
     */
    @Override
    public void update(Book updatedBook) throws DAOException {
        Connection connection = null;
        PreparedStatement prBook = null;
        PreparedStatement prTranslationBook = null;
        try {

            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            prBook = connection.prepareStatement(UPDATE_BOOK);
            prTranslationBook = connection.prepareStatement(UPDATE_BOOK_TRANSLATION);

            prBook.setInt(1, updatedBook.getYear());
            prBook.setString(2, updatedBook.getPublishingHouse());
            prBook.setInt(3, updatedBook.getNumber());
            prBook.setLong(4, updatedBook.getId());
            prBook.executeUpdate();

            for (int i = 0; i < updatedBook.getBookTranslations().size(); i++) {
                prTranslationBook.setString(1, updatedBook.getBookTranslations().get(i).getTitle());
                prTranslationBook.setString(2, updatedBook.getBookTranslations().get(i).getAuthor());
                prTranslationBook.setLong(3, updatedBook.getId());
                prTranslationBook.setString(4, updatedBook.getBookTranslations().get(i).getLanguage().getName());
                prTranslationBook.executeUpdate();
            }
            ConnectionUtil.closeTransaction(connection);
            logger.info("The 'update' function has been completed");
        } catch (SQLException e) {
            ConnectionUtil.rollback(connection);
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        } finally {
            ConnectionUtil.closeStatement(prBook);
            ConnectionUtil.closeStatement(prTranslationBook);
            ConnectionUtil.closeConnection(connection);
        }
    }

    /**
     * The function which delete the book from database by book id.
     *
     * @param id - id of book.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Book
     */
    @Override
    public void delete(long id) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(DELETE_BOOK)) {
            pr.setLong(1, id);
            pr.executeUpdate();
            logger.info("The 'delete' function has been completed");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        }
    }

    /**
     * The function which add a new book to database.
     *
     * @param book - the book which will be added to database.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Book
     */
    @Override
    public void add(Book book) throws DAOException {
        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement prAddNewBook = null;
        PreparedStatement prAddTranslation = null;
        try {

            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            prAddNewBook = connection.prepareStatement(ADD_NEW_BOOK, Statement.RETURN_GENERATED_KEYS);
            prAddTranslation = connection.prepareStatement(INSERT_TRANSLATION_FOR_BOOK);

            prAddNewBook.setInt(1, book.getYear());
            prAddNewBook.setString(2, book.getPublishingHouse());
            prAddNewBook.setInt(3, book.getNumber());
            prAddNewBook.executeUpdate();

            rs = prAddNewBook.getGeneratedKeys();
            if (rs.next()) {
                book.setId(rs.getInt(1));
            }

            for (int i = 0; i < book.getBookTranslations().size(); i++) {
                prAddTranslation.setLong(1, i + 1);
                prAddTranslation.setLong(2, book.getId());
                prAddTranslation.setString(3, book.getBookTranslations().get(i).getTitle());
                prAddTranslation.setString(4, book.getBookTranslations().get(i).getAuthor());
                prAddTranslation.executeUpdate();
            }
            ConnectionUtil.closeTransaction(connection);
            logger.info("The 'add' function has been completed");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            ConnectionUtil.rollback(connection);
            throw new DAOException("Database query error", e);
        } finally {
            ConnectionUtil.closeResultSet(rs);
            ConnectionUtil.closeStatement(prAddNewBook);
            ConnectionUtil.closeStatement(prAddTranslation);
        }
    }

}
