package app.model.dao;

import app.model.dao.impl.*;

public class DAOFactory {

    /**
     * DAOFactory instance.
     */
    private static DAOFactory instance = new DAOFactory();
    /**
     * {@link UserDAO} field.
     */
    private final UserDAO userDAO = new UserDAOImpl();
    /**
     * {@link BookDAO} field.
     */
    private final BookDAO bookDAO = new BookDAOImpl();
    /**
     * {@link LibrarianDAO} field.
     */
    private final LibrarianDAO librarianDAO = new LibrarianDAOImpl();
    /**
     * {@link PassDAO} field.
     */
    private final PassDAO passDAO = new PassDAOImpl();
    /**
     * {@link OrderDAO} field.
     */
    private final OrderDAO orderDAO = new OrderDAOImpl();

    private DAOFactory() {
    }

    /**
     * Return DAOFactory instance.
     *
     * @return DAOFactory instance.
     */
    public synchronized static DAOFactory getInstance() {
        if (instance == null)
            instance = new DAOFactory();
        return instance;
    }

    /**
     * Return {@link  UserDAO} implementation.
     *
     * @return {@link  UserDAO} implementation.
     */
    public static UserDAO getUserDAO() {
        return getInstance().userDAO;
    }

    /**
     * Return {@link  BookDAO} implementation.
     *
     * @return {@link  BookDAO} implementation.
     */
    public static BookDAO getBookDAO() {
        return getInstance().bookDAO;
    }

    /**
     * Return {@link  LibrarianDAO} implementation.
     *
     * @return {@link LibrarianDAO} implementation.
     */
    public static LibrarianDAO getLibrarianDAO() {
        return getInstance().librarianDAO;
    }

    /**
     * Return {@link  PassDAO} implementation.
     *
     * @return {@link PassDAO} implementation.
     */
    public static PassDAO getPassDAO() {
        return getInstance().passDAO;
    }

    /**
     * Return {@link  OrderDAO} implementation.
     *
     * @return {@link OrderDAO} implementation.
     */
    public static OrderDAO getOrderDAO() {
        return getInstance().orderDAO;
    }
}
