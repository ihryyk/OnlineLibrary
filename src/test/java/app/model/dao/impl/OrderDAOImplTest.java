package app.model.dao.impl;

import app.model.dao.DAOFactory;
import app.model.dao.OrderDAO;
import app.model.dao.exeption.DAOException;
import app.model.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static app.model.dao.impl.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderDAOImplTest {

    OrderDAO orderDAO = DAOFactory.getOrderDAO();

    @BeforeEach
    public void runScript() {
        try {
            RunSqlScript.RunSqlScript();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void cancel() throws DAOException {
        orderDAO.cancel(50);
        Order actual = orderDAO.getById("eng", 50);
        Order expected = cancelOrder;
        assertEquals(actual.toString(), expected.toString());
    }

    @Test
    void changeState() throws DAOException {
        orderDAO.submit(50);
        Order actual = orderDAO.getById("eng", 50);
        Order expected = submittedOrder;
        assertEquals(actual.toString(), expected.toString());
    }

    @Test
    void getAllByUserId() throws DAOException {
        List<Order> expected = new ArrayList<Order>(Arrays.asList(order));
        List<Order> actual = orderDAO.getAllByUserId(14, "eng");
        assertEquals(actual.size(), expected.size());
        assertEquals(actual.toString(), expected.toString());
    }

    @Test
    void getById() throws DAOException {
        Order expected = order;
        Order actual = orderDAO.getById("eng", 50 );
        assertEquals(actual.toString(), expected.toString());
    }

    @Test
    void getAll() throws DAOException {
        List<Order> expected = new ArrayList<Order>(Arrays.asList(order));
        List<Order> actual = orderDAO.getAll("eng");
        assertEquals(actual.size(), expected.size());
        assertEquals(actual.toString(), expected.toString());
    }

    @Test
    void create() throws DAOException {
        orderDAO.create(order);
        List<Order> expected = new ArrayList<Order>(Arrays.asList(order, inProgressingOrder));
        List<Order> actual = orderDAO.getAll("eng");
        assertEquals(actual.size(), expected.size());
        assertEquals(actual.toString(), expected.toString());
    }
}