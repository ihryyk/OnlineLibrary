package app.model.dao.impl;

import app.model.dao.DAOFactory;
import app.model.dao.PassDAO;
import app.model.dao.exeption.DAOException;
import app.model.entity.Book;
import app.model.entity.BookTranslation;
import app.model.entity.Pass;
import app.model.enums.PassStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static app.model.dao.impl.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PassDAOImplTest {

    PassDAO passDAO = DAOFactory.getPassDAO();

    @BeforeEach
    public void runScript() {
        try {
            RunSqlScript.RunSqlScript();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void finishPass() throws DAOException {
        passDAO.finishPass(pass.getId());
        Pass actual = passDAO.getAllByUserId(14, "eng").get(0);
        Pass expected = finishPass;
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void getBooksById() throws DAOException {
        List<BookTranslation> bookTranslations = new ArrayList<>();
        List<Book> actual = passDAO.getBooksById(pass.getId());
        List<Book> expected = Arrays.asList(new Book(1, 2021, "Folio", 18, bookTranslations));
        assertEquals(expected.toString(), actual.toString());

    }

    @Test
    void create() throws DAOException {
        passDAO.create(pass);
        List<Pass> expected = Arrays.asList(pass, pass);
        List<Pass> actual = passDAO.getAll("eng");
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void getAll() throws DAOException {
        List<Pass> expected =Arrays.asList(pass);
        List<Pass> actual = passDAO.getAll("eng");
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void getAllByUserId() throws DAOException {
        List<Pass> expected = Arrays.asList(pass);
        List<Pass> actual = passDAO.getAllByUserId(14, "eng");
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(), actual.toString());
    }
    @Test
    void add() throws DAOException {
        Pass expected  = new Pass(35, Date.valueOf("2022-03-28"),
                Date.valueOf("2022-03-31"), PassStatus.ACTIVE, 20,order) ;
        passDAO.addPenalty(20, expected.getId());
        Pass actual = passDAO.getAllByUserId(14, "eng").get(0);
        assertEquals(expected.toString(), actual.toString());

    }
}