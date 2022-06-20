package app.model.dao;

import app.model.dao.exeption.DAOException;
import app.model.entity.Order;

import java.util.List;

/**
 * The interface defines methods for implementing different
 * activities with order
 *
 * @author Ihor Berezovskyi
 */
public interface OrderDAO {
    /**
     * Returns the list of all orders by user id.
     *
     * @param userID - the id of user.
     * @return - the list of all orders by user id
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Order
     */
    public List<Order> getAllByUserId(long userID, String language) throws DAOException;

    /**
     * The function which add a new order to database.
     *
     * @param order - the order which will be added to database.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Order
     */
    public void create(Order order) throws DAOException;

    /**
     * The function which changes the order status to 'canceled'.
     *
     * @param orderId - the id of order.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Order
     */
    public void cancel(int orderId) throws DAOException;

    /**
     * Returns the list of order for specified language.
     *
     * @param language - language for the book information.
     * @return - the list of order for specified language
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Order
     */
    public List<Order> getAll(String language) throws DAOException;

    /**
     * Returns the order by order id.
     *
     * @param orderId - the id of order.
     * @return - the order by order id
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Order
     */
    public Order getById(String language, long orderId) throws DAOException;

    /**
     * The function which changes the order status to 'submitted'.
     *
     * @param orderId - the id of order.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Order
     */
    public void submit(long orderId) throws DAOException;
}
