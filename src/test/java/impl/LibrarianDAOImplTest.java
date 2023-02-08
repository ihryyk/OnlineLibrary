package impl;

import app.model.dao.DAOFactory;
import app.model.dao.LibrarianDAO;
import app.model.dao.exeption.DAOException;
import app.model.entity.Role;
import app.model.entity.User;
import app.model.enums.LockStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static impl.TestData.librarian;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LibrarianDAOImplTest {
    LibrarianDAO librarianDAO = DAOFactory.getLibrarianDAO();

    @BeforeEach
    public void runScript() {
        try {
            RunSqlScript.RunSqlScript();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createNew() throws DAOException {
        User newLibrarian = new User(52, "librarian", "newLibrarian@gmail.com",
                "7c4a8d09ca3762af61e59520943dc26494f8941b", new Role(3, "librarian"), LockStatus.UNLOCKED);
        librarianDAO.createNew(newLibrarian);
        List<User> actual = librarianDAO.getAll();
        List<User> expected = new ArrayList<>(Arrays.asList(librarian, newLibrarian));
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void delete() throws DAOException {
        librarianDAO.delete(librarian.getId());
        List<User> actual = librarianDAO.getAll();
        List<User> expected = new ArrayList<>();
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(), actual.toString());

    }

    @Test
    void getAll() throws DAOException {
        List<User> actual = librarianDAO.getAll();
        List<User> expected = Arrays.asList(librarian);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(), actual.toString());
    }
}