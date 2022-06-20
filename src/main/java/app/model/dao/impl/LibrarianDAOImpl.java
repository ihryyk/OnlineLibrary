package app.model.dao.impl;

import app.model.dao.LibrarianDAO;
import app.model.dao.exeption.DAOException;
import app.model.dao.util.ConnectionPool;
import app.model.dao.util.ConnectionUtil;
import app.model.dao.util.UserUtil;
import app.model.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static app.model.dao.util.SqlConstant.LibrarianRequestQuery.*;

/**
 * Implement an interface that defines different activities with user-librarian in database.
 *
 * @author Ihor Berezovskyi
 */
public class LibrarianDAOImpl implements LibrarianDAO {
    Logger logger = Logger.getLogger(LibrarianDAOImpl.class);

    /**
     * The function which add a new user-librarian to database.
     *
     * @param librarian - the user-librarian which will be added to database.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see User
     */
    @Override
    public void createNew(User librarian) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(INSERT_NEW_LIBRARIAN)) {
            pr.setString(1, librarian.getName());
            pr.setString(2, librarian.getEmailAddress());
            pr.setString(3, librarian.getPassword());
            pr.executeUpdate();
            logger.info("The 'createNew' function has been completed");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        }
    }

    /**
     * The function which delete the user-librarian from database by book id.
     *
     * @param librarianId - id of user-librarian.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see User
     */
    @Override
    public void delete(long librarianId) throws DAOException {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_LIBRARIAN)) {
            ps.setLong(1, librarianId);
            ps.executeUpdate();
            logger.info("The 'delete' function has been completed");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        }
    }

    /**
     * The function which returns the list of all users-librarians from database.
     *
     * @return list of all user-librarian
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see User
     */
    @Override
    public List<User> getAll() throws DAOException {
        ResultSet rs = null;
        List<User> librarians = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(GET_ALL_LIBRARIANS)) {
            rs = pr.executeQuery();
            while (rs.next()) {
                librarians.add(UserUtil.userInitialization(rs));
            }
            logger.info("The result of the 'getAll' function is obtained");
            return librarians;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        } finally {
            ConnectionUtil.closeResultSet(rs);
        }
    }
}
