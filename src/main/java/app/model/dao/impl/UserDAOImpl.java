package app.model.dao.impl;

import app.model.dao.UserDAO;
import app.model.dao.exeption.DAOException;
import app.model.dao.util.ConnectionPool;
import app.model.dao.util.ConnectionUtil;
import app.model.dao.util.UserUtil;
import app.model.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static app.model.dao.util.SqlConstant.UserRequestQuery.*;
/**
 * Implement an interface that defines different activities with user in database.
 *
 * @author Ihor Berezovskyi
 */
public class UserDAOImpl implements UserDAO {

    Logger logger = Logger.getLogger(UserDAOImpl.class);
    /**
     * The function which add a new user to database.
     *
     * @param user - the user-librarian which will be added to database.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see User
     */
    @Override
    public void add(User user) throws DAOException {
        ResultSet rs = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS)) {
            pr.setString(1, user.getName());
            pr.setString(2, user.getEmailAddress());
            pr.setString(3, user.getPassword());
            rs = pr.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
            pr.executeUpdate();
            logger.info("The 'add' function has been completed");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        } finally {
            ConnectionUtil.closeResultSet(rs);
        }
    }

    /**
     * The function which updates information about the user.
     *
     * @param user - a user with new information.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see User
     */
    @Override
    public void update(User user) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(UPDATE_USER)) {
            pr.setString(1, user.getName());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getEmailAddress());
            pr.executeUpdate();
            logger.info("The 'update' function has been completed");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        }
    }

    /**
     * Returns information about user by email and password
     *
     * @param email   - user's email.
     * @param password - user's password.
     * @return information about user.
     * @throws DAOException if there was an error executing the query
     *                      in the database
     * @see User
     */
    @Override
    public User getByEmailAndPassword(String email, String password) throws DAOException {
        ResultSet rs = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(IS_USER_LOGGED)) {
            pr.setString(1, email);
            pr.setString(2, password);
            rs = pr.executeQuery();
            if (rs.next()) {
                logger.info("The result of the 'getByEmailAndPassword' function is obtained");
                return UserUtil.userInitialization(rs);
            }
            logger.info("The result of the 'getByEmailAndPassword' function is not obtained");
            return null;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        } finally {
            ConnectionUtil.closeResultSet(rs);
        }
    }

    /**
     * Returns information about user by email
     *
     * @param email   - user's email.
     * @return information about user.
     * @throws DAOException if there was an error executing the query
     *                      in the database
     * @see User
     */
    @Override
    public User getByEmail(String email) throws DAOException {
        ResultSet rs = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(GET_USER_BY_EMAIL)) {
            pr.setString(1, email);
            rs = pr.executeQuery();
            if (rs.next()) {
                logger.info("The result of the 'getByEmail' function is obtained");
                return UserUtil.userInitialization(rs);
            }
            logger.info("The result of the 'getByEmail' function is not obtained");
            return null;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        } finally {
            ConnectionUtil.closeResultSet(rs);
        }
    }

    /**
     * The function which returns the list of all users from database.
     *
     * @return list of all user
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see User
     */
    @Override
    public List<User> getAll() throws DAOException {
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(SELECT_ALL_USERS)) {
            rs = pr.executeQuery();
            while (rs.next()) {
                users.add(UserUtil.userInitialization(rs));
            }
            logger.info("The result of the 'getAll' function at UserDaoImpl is not obtained");
            return users;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        } finally {
            ConnectionUtil.closeResultSet(rs);
        }
    }
    /**
     * The function which changes the lock status to 'block'.
     *
     * @param userId - the user's id by which user will get blocked.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see User
     */
    @Override
    public void block(long userId) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(BLOCK_USER)) {
            pr.setLong(1, userId);
            pr.executeUpdate();
            logger.info("The 'block' function has been completed");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        }
    }
    /**
     * The function which changes the lock status to 'unlocked'.
     *
     * @param userId - the user's id by which user will get unlocked.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see User
     */
    @Override
    public void unlock(long userId) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(UNLOCK_USER)) {
            pr.setLong(1, userId);
            pr.executeUpdate();
            logger.info("The 'unlock' function has been completed");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        }
    }
    /**
     * The function which delete the user-librarian from database by book id.
     *
     * @param user - the user which will be deleted.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see User
     */
    @Override
    public void delete(User user) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(DELETE_USER)) {
            pr.setString(1, user.getEmailAddress());
            pr.executeUpdate();
            logger.info("The 'delete' function has been completed");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        }
    }
}
