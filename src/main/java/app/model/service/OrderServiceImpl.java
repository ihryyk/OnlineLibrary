package app.model.service;

import app.controller.servlets.util.validate.ArgumentValidator;
import app.model.dao.DAOFactory;
import app.model.dao.OrderDAO;
import app.model.dao.exeption.DAOException;
import app.model.dao.exeption.ServiceException;
import app.model.entity.Order;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    Logger logger = Logger.getLogger(OrderServiceImpl.class);
    OrderDAO orderDAO = DAOFactory.getOrderDAO();

    /**
     * The function which changes the order status to 'canceled'.
     *
     * @param orderId - the id of order.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see Order
     */
    public synchronized void cancel(int orderId) throws ServiceException {
        try {
            orderDAO.cancel(orderId);
            logger.info("The 'cancel' function has been completed");
        } catch (DAOException e) {
            throw new ServiceException("exception in cancel at OrderService class", e);
        }
    }

    /**
     * Returns the list of all orders by user id.
     *
     * @param userID - the id of user.
     * @return - the list of all orders by user id
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see Order
     */
    public List<Order> getAllByUserId(long userID, String language) throws ServiceException {
        try {
            List<Order> orders = orderDAO.getAllByUserId(userID, language);
            logger.info("The result of the 'getAllByUserId' function is obtained");
            return orders;
        } catch (DAOException e) {
            throw new ServiceException("exception in etAllByUserId at OrderService class", e);
        }
    }

    /**
     * Returns the order by order id.
     *
     * @param orderId - the id of order.
     * @return - the order by order id
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see Order
     */
    public Order getById(String language, long orderId) throws ServiceException {
        try {
            Order order = orderDAO.getById(language, orderId);
            logger.info("The result of the 'getById' function is obtained");
            return order;
        } catch (DAOException e) {
            throw new ServiceException("exception in getById at OrderService class", e);
        }
    }

    /**
     * Returns the list of order for specified language.
     *
     * @param language - language for the book information.
     * @return - the list of order for specified language
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see Order
     */
    public List<Order> getAll(String language) throws ServiceException {
        try {
            List<Order> orders = orderDAO.getAll(language);
            logger.info("The result of the 'getAll' function is obtained");
            return orders;
        } catch (DAOException e) {
            throw new ServiceException("exception in getAll at OrderService class", e);
        }
    }

    /**
     * The function which add a new order to database.
     *
     * @param order - the order which will be added to database.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see Order
     */
    public void create(Order order) throws ServiceException {
        ArgumentValidator.checkForNull(order, "Not allow for a null order in create at orderService class");
        try {
            orderDAO.create(order);
            logger.info("The 'create' function has been completed");
        } catch (DAOException e) {
            throw new ServiceException("exception in create at OrderService class", e);
        }
    }

    /**
     * The function which changes the order status to 'submitted'.
     *
     * @param orderId - the id of order.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see Order
     */
    public synchronized void submit(long orderId) throws ServiceException {
        try {
            orderDAO.submit(orderId);
            logger.info("The 'submit' function has been completed");
        } catch (DAOException e) {
            throw new ServiceException("exception in submit at OrderService class", e);
        }
    }
}
