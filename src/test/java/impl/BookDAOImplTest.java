package impl;

import app.model.dao.BookDAO;
import app.model.dao.DAOFactory;
import app.model.dao.exeption.DAOException;
import app.model.dao.util.ConnectionPool;
import app.model.entity.Book;
import app.model.entity.BookTranslation;
import app.model.entity.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static app.model.dao.util.SqlConstant.BookRequestQuery.GROUP_BOOK_BY_YEAR;
import static impl.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BookDAOImplTest {
    BookDAO bookDAO = DAOFactory.getBookDAO();

    @BeforeEach
    public void runScript() {
        try {
            RunSqlScript.RunSqlScript();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getById() throws DAOException {
        Book actual = bookDAO.getById(1, "eng");
        List<BookTranslation> bookTranslations = new ArrayList<>();
        bookTranslations.add(ENGbt1);
        b1.setBookTranslations(bookTranslations);
        Book expected = b1;
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    void getByAuthor() throws DAOException {
        List<Book> expected = new ArrayList<>(Arrays.asList(b1));
        List<Book> actual = bookDAO.getByAuthor("Ivan Bahrianyi", "eng", 0, 5);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void getCount() throws DAOException {
        int actual = bookDAO.getCount();
        int expected = 9;
        assertEquals(expected, actual);
    }

    @Test
    void getCountBooksByTitle() throws DAOException {
        int actual = bookDAO.getCountBooksByTitle("Tiger hunters");
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void getCountBooksByAuthor() throws DAOException {
        int actual = bookDAO.getCountBooksByAuthor("Ivan Bahrianyi");
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void getAll() throws DAOException {
        List<Book> expected = allBooks;
        List<Book> actual = bookDAO.getAll("eng", 0, 9);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void getAllForAdmin() throws DAOException {
        List<Book> expected = allAdminBooks;
        List<Book> actual = bookDAO.getAllForAdmin(0, 9);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void getTranslation() throws DAOException {
        List<BookTranslation> expected = bkList1;
        List<BookTranslation> actual = bookDAO.getTranslation(ConnectionPool.getInstance().getConnection(), 1);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void getByTitle() throws DAOException {
        List<Book> expected = new ArrayList<>(Arrays.asList(b1));
        List<Book> actual = bookDAO.getByTitle("Tiger hunters", "eng", 0, 5);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void sortBy() throws DAOException {
        List<Book> expected = new ArrayList<>(Arrays.asList(b1, b9, b2, b3, b4));
        List<Book> actual = bookDAO.sortBy(GROUP_BOOK_BY_YEAR, "eng", 0, 5);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void changeNumber() throws DAOException {
        int expected = 19;
        bookDAO.changeNumber(b1.getId(), -1);
        Book book = bookDAO.getById(b1.getId(), "eng");
        int actual = book.getNumber();
        assertEquals(expected, actual);
    }

    @Test
    void update() throws DAOException {
        Book expected = new Book(1, 1, "1", 1,
                new ArrayList<>(Collections.singletonList(new BookTranslation("1", new Language(1, "eng"), "1"))));
        bookDAO.update(expected);
        Book actual = bookDAO.getById(1, "eng");
        bookDAO.update(b1);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void delete() throws DAOException {
        List<Book> expected = new ArrayList<Book>(Arrays.asList(b2, b3, b4, b5, b6, b7, b8, b9));
        bookDAO.delete(1);
        List<Book> actual = bookDAO.getAll("eng", 0, 9);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void add() throws DAOException {
        List<Book> expected = new ArrayList<>(Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8, b9, b1));
        bookDAO.add(b1);
        List<Book> actual = bookDAO.getAll("eng", 0, 10);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(), actual.toString());
    }
}