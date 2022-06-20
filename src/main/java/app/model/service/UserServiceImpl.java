package app.model.service;

import app.controller.servlets.util.validate.ArgumentValidator;
import app.model.dao.DAOFactory;
import app.model.dao.UserDAO;
import app.model.dao.exeption.DAOException;
import app.model.dao.exeption.ServiceException;
import app.model.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import java.util.List;


public class UserServiceImpl implements UserService {
    Logger logger = Logger.getLogger(UserServiceImpl.class);
    UserDAO userDAO = DAOFactory.getUserDAO();

    /**
     * The function which add a new user to database.
     *
     * @param user - the user-librarian which will be added to database.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see User
     */
    public void add(User user) throws ServiceException {
        ArgumentValidator.checkForNull(user, "Not allow for a null user in add at userService class");
        try {
            String encryptPassword = DigestUtils.sha1Hex(user.getPassword());
            user.setPassword(encryptPassword);
            userDAO.add(user);
            logger.info("The 'add' function has been completed");
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in add at userService class", e);
        }
    }

    /**
     * The function which updates information about the user.
     *
     * @param user - a user with new information.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see User
     */
    public void update(User user) throws ServiceException {
        ArgumentValidator.checkForNull(user, "Not allow for a null user in update at userService class");
        try {
            String encryptPassword = DigestUtils.sha1Hex(user.getPassword());
            user.setPassword(encryptPassword);
            userDAO.update(user);
            logger.info("The 'update' function has been completed");
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in update at userService class", e);
        }
    }

    /**
     * Returns information about user by email and password
     *
     * @param email    - user's email.
     * @param password - user's password.
     * @return information about user.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see User
     */
    public User getByEmailAndPassword(String email, String password) throws ServiceException {
        try {
            String encryptPassword = DigestUtils.sha1Hex(password);
            User user = userDAO.getByEmailAndPassword(email, encryptPassword);
            logger.info("The result of the 'getByEmailAndPassword' function is obtained");
            return user;
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in update at userService class", e);
        }
    }

    /**
     * Returns information about user by email
     *
     * @param email - user's email.
     * @return information about user.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see User
     */
    public User getByEmail(String email) throws ServiceException {
        try {
            User user = userDAO.getByEmail(email);
            logger.info("The result of the 'getByEmail' function is obtained");
            return user;

        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in getByEmail at userService class", e);
        }
    }

    /**
     * The function which returns the list of all users from database.
     *
     * @return list of all user
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see User
     */
    public List<User> getAll() throws ServiceException {
        try {
            List<User> users = userDAO.getAll();
            logger.info("The result of the 'getAll' function at UserDaoImpl is not obtained");
            return users;
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in getAll at userService class", e);
        }
    }

    /**
     * The function which changes the lock status to 'block'.
     *
     * @param userId - the user's id by which user will get blocked.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see User
     */
    public synchronized void block(long userId) throws ServiceException {
        try {
            userDAO.block(userId);
            logger.info("The 'block' function has been completed");
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in block at userService class", e);
        }
    }

    /**
     * The function which changes the lock status to 'unlocked'.
     *
     * @param userId - the user's id by which user will get unlocked.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see User
     */
    public synchronized void unlock(long userId) throws ServiceException {
        try {
            userDAO.unlock(userId);
            logger.info("The 'unlock' function has been completed");
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in unlock at userService class", e);
        }
    }

    /**
     * The function which delete the user-librarian from database by book id.
     *
     * @param user - the user which will be deleted.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see User
     */
    public void delete(User user) throws ServiceException {
        ArgumentValidator.checkForNull(user, "Not allow for a null user in delete at userService class");
        try {
            userDAO.delete(user);
            logger.info("The 'delete' function has been completed");
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in delete at userService class", e);
        }
    }
}
