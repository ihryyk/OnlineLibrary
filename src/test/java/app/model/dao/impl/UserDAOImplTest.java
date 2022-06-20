package app.model.dao.impl;

import app.model.dao.DAOFactory;
import app.model.dao.UserDAO;
import app.model.dao.exeption.DAOException;
import app.model.entity.Role;
import app.model.entity.User;
import app.model.enums.LockStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static app.model.dao.impl.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDAOImplTest {

    UserDAO userDAO = DAOFactory.getUserDAO();


    @BeforeEach
    public void runScript() {
        try {
            RunSqlScript.RunSqlScript();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void add() throws DAOException {
        User expected = new User(10, "user", "newUser@gmail.com",
                "7c4a8d09ca3762af61e59520943dc26494f8941b", new Role(1, "user"), LockStatus.UNLOCKED);
        userDAO.add(expected);
        User actual = userDAO.getByEmail("newUser@gmail.com");
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void update() throws DAOException {
        User expected = new User(14, "testUser", "123467@gmail.com",
                "7c4a8d09ca3762af61e59520943dc26494f8941b", new Role(1, "user"), LockStatus.UNLOCKED);
        userDAO.update(expected);
        User actual = userDAO.getByEmail("123467@gmail.com");
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void getByEmailAndPassword() throws DAOException {
        User expected = user;
        User actual = userDAO.getByEmailAndPassword("123467@gmail.com",
                "7c4a8d09ca3762af61e59520943dc26494f8941b");
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void getByEmail() throws DAOException {
        User expected = user;
        User actual = userDAO.getByEmail("123467@gmail.com");
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void getAll() throws DAOException {
        List<User> expected = Arrays.asList(user, admin, librarian);
        List<User> actual = userDAO.getAll();
        assertEquals(expected.toString(), actual.toString());

    }

    @Test
    void block() throws DAOException {
        userDAO.block(user.getId());
        User expected = new User(14, "IhorBerezovskyi",
                "123467@gmail.com", "" +
                "7c4a8d09ca3762af61e59520943dc26494f8941b", userRole, LockStatus.BLOCKED);
        User actual = userDAO.getByEmail("123467@gmail.com");
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void unlock() throws DAOException {
        userDAO.unlock(blockUser.getId());
        User expected = new User(14, "IhorBerezovskyi", "123467@gmail.com",
                "7c4a8d09ca3762af61e59520943dc26494f8941b", userRole, LockStatus.UNLOCKED);
        User actual = userDAO.getByEmail("123467@gmail.com");
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void delete() throws DAOException {
        userDAO.delete(user);
        List<User> expected = Arrays.asList(admin, librarian);
        List<User> actual = userDAO.getAll();
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(), actual.toString());
    }
}