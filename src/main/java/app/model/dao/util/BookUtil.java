package app.model.dao.util;

import app.model.dao.exeption.DAOException;
import app.model.entity.Book;
import app.model.entity.BookTranslation;
import app.model.entity.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static app.model.dao.util.SqlConstant.*;

/**
 * Used to store information about books from database.
 *
 * @author Ihor Berezovskyi
 */
public class BookUtil {

    /**
     * The function which store information about book translation.
     *
     * @param rs - result set.
     * @return initialized bookTranslation object.
     * @throws SQLException if there was database access error or other errors.
     */
    public static BookTranslation bookTranslationInitialization(ResultSet rs) throws SQLException {
        BookTranslation bookTranslation = new BookTranslation();
        Language language = new Language();
        language.setId(rs.getLong("language_id"));
        language.setName(rs.getString("name_language"));
        bookTranslation.setLanguage(language);
        bookTranslation.setTitle(rs.getString("title"));
        bookTranslation.setAuthor(rs.getString("author"));
        return bookTranslation;
    }

    /**
     * The function which store information about book.
     *
     * @param rs - result set.
     * @return initialized book object.
     * @throws SQLException if there was database access error or other errors.
     */
    public static Book bookInitialization(ResultSet rs) throws SQLException {

        Book book = new Book();
        book.setId(rs.getInt(BOOK_ID));
        book.setYear(rs.getInt(BOOK_YEAR));
        book.setPublishingHouse(rs.getString(PUBLISHING_HOUSE));
        book.setNumber(rs.getInt(BOOK_NUMBER));

        Language lang = new Language();
        lang.setId(rs.getInt(LANGUAGE_ID));
        lang.setName(rs.getString(LANGUAGE_NAME));

        BookTranslation bookTranslation = new BookTranslation();
        bookTranslation.setLanguage(lang);
        bookTranslation.setTitle(rs.getString(TITLE));
        bookTranslation.setAuthor(rs.getString(AUTHOR));
        book.getBookTranslations().add(bookTranslation);
        book.setDeleted(rs.getBoolean(DELETED));
        return book;
    }

    /**
     * @param selectedID  - id of order.
     * @param connection  - connection.
     * @param SQL_REQUEST - database query.
     * @param language    - language.
     * @return list of books by order id.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     */
    static public List<Book> getAllSelectedBooks(long selectedID, Connection connection, String SQL_REQUEST, String language) throws DAOException {
        List<Book> books = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST);) {
            preparedStatement.setLong(1, selectedID);
            preparedStatement.setString(2, language);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                books.add(BookUtil.bookInitialization(resultSet));
            }
            return books;

        } catch (SQLException e) {
            throw new DAOException("Database query error", e);
        } finally {
            ConnectionUtil.closeResultSet(resultSet);
        }
    }

}
