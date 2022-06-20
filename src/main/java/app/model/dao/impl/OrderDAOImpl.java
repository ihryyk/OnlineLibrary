package app.model.dao.impl;

import app.model.dao.OrderDAO;
import app.model.dao.exeption.DAOException;
import app.model.dao.util.BookUtil;
import app.model.dao.util.ConnectionPool;
import app.model.dao.util.ConnectionUtil;
import app.model.dao.util.OrderUtil;
import app.model.entity.Order;
import app.model.enums.OrderStatus;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static app.model.dao.util.SqlConstant.BookRequestQuery.ADD_BOOK_TO_ORDER;
import static app.model.dao.util.SqlConstant.BookRequestQuery.GET_ALL_ORDER_BOOKS;
import static app.model.dao.util.SqlConstant.OrderRequestQuery.*;

/**
 * Implement an interface that defines different activities with order in database.
 *
 * @author Ihor Berezovskyi
 */
public class OrderDAOImpl implements OrderDAO {
    Logger logger = Logger.getLogger(OrderDAOImpl.class);

    /**
     * The function which changes the order status to 'canceled'.
     *
     * @param orderId - the id of order.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Order
     */
    @Override
    public void cancel(int orderId) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(CANCEL_ORDER)) {
            pr.setLong(1, orderId);
            pr.executeUpdate();
            logger.info("The 'cancel' function has been completed");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        }
    }


    /**
     * The function which changes the order status to 'submitted'.
     *
     * @param orderId - the id of order.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Order
     */
    @Override
    public void submit(long orderId) throws DAOException {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(CHANGE_ORDER_STATE)) {
            pr.setString(1, OrderStatus.SUBMITTED.name());
            pr.setLong(2, orderId);
            pr.executeUpdate();
            logger.info("The 'submit' function has been completed");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        }
    }

    /**
     * Returns the list of all orders by user id.
     *
     * @param userID - the id of user.
     * @return - the list of all orders by user id
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Order
     */
    @Override
    public List<Order> getAllByUserId(long userID, String language) throws DAOException {
        List<Order> orders = new ArrayList<>();
        Order order = null;
        ResultSet rs = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(SELECT_ALL_ORDER_BY_USER_ID);) {
            pr.setLong(1, userID);
            rs = pr.executeQuery();
            while (rs.next()) {
                order = OrderUtil.orderInitialization(rs);
                order.setOrderBooks(BookUtil.getAllSelectedBooks(order.getId(), connection, GET_ALL_ORDER_BOOKS, language));
                orders.add(order);
            }
            logger.info("The result of the 'getAllByUserId' function is obtained");
            return orders;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        } finally {
            ConnectionUtil.closeResultSet(rs);
        }
    }

    /**
     * Returns the order by order id.
     *
     * @param orderId - the id of order.
     * @return - the order by order id
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Order
     */
    public Order getById(String language, long orderId) throws DAOException {
        ResultSet rs = null;
        Order order = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(SELECT_ALL_ORDER_BI_ID)) {
            pr.setLong(1, orderId);
            rs = pr.executeQuery();
            while (rs.next()) {
                order = OrderUtil.orderInitialization(rs);
                order.setOrderBooks(BookUtil.getAllSelectedBooks(order.getId(), connection, GET_ALL_ORDER_BOOKS, language));
            }
            logger.info("The result of the 'getById' function is obtained");
            return order;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        } finally {
            ConnectionUtil.closeResultSet(rs);
        }
    }

    /**
     * Returns the list of order for specified language.
     *
     * @param language - language for the book information.
     * @return - the list of order for specified language
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Order
     */
    public List<Order> getAll(String language) throws DAOException {
        List<Order> orders = new ArrayList<>();
        ResultSet rs = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(SELECT_ALL_ORDER);) {
            rs = pr.executeQuery();
            while (rs.next()) {
                Order order = OrderUtil.orderInitialization(rs);
                order.setOrderBooks(BookUtil.getAllSelectedBooks(order.getId(), connection, GET_ALL_ORDER_BOOKS, language));
                orders.add(order);
            }
            logger.info("The result of the 'getAll' function is obtained");
            return orders;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        } finally {
            ConnectionUtil.closeResultSet(rs);
        }
    }

    /**
     * The function which add a new order to database.
     *
     * @param order - the order which will be added to database.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Order
     */
    @Override
    public void create(Order order) throws DAOException {
        Connection connection = null;
        PreparedStatement prCreate = null;
        PreparedStatement prAdd = null;
        ResultSet rs = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            prCreate = connection.prepareStatement(CREATE_ORDER, Statement.RETURN_GENERATED_KEYS);

            prAdd = connection.prepareStatement(ADD_BOOK_TO_ORDER);


            prCreate.setLong(1, order.getUser().getId());
            prCreate.setString(2, order.getBookOption().name());
            prCreate.setString(3, OrderStatus.IN_PROCESSING.toString());
            prCreate.setDate(4, order.getArrivalDate());
            prCreate.executeUpdate();

            rs = prCreate.getGeneratedKeys();
            if (rs.next()) {
                order.setId(rs.getInt(1));
            }


            for (int i = 0; i < order.getOrderBooks().size(); i++) {
                prAdd.setLong(1, order.getId());
                prAdd.setLong(2, order.getOrderBooks().get(i).getId());
                prAdd.executeUpdate();
            }
            ConnectionUtil.closeTransaction(connection);
            logger.info("The 'create' function has been completed");
        } catch (SQLException e) {
            ConnectionUtil.rollback(connection);
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        } finally {
            ConnectionUtil.closeStatement(prAdd);
            ConnectionUtil.closeStatement(prCreate);
            ConnectionUtil.closeConnection(connection);
            ConnectionUtil.closeResultSet(rs);
        }
    }

}
