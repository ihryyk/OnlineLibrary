package app.model.dao;

import app.model.dao.exeption.DAOException;
import app.model.entity.User;

import java.util.List;

/**
 * The interface defines methods for implementing different
 * activities with user
 *
 * @author Ihor Berezovskyi
 */
public interface UserDAO {
    /**
     * The function which add a new user to database.
     *
     * @param user - the user-librarian which will be added to database.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see User
     */
    public void add(User user) throws DAOException;

    /**
     * The function which updates information about the user.
     *
     * @param user - a user with new information.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see User
     */
    public void update(User user) throws DAOException;

    /**
     * Returns information about user by email and password
     *
     * @param email    - user's email.
     * @param password - user's password.
     * @return information about user.
     * @throws DAOException if there was an error executing the query
     *                      in the database
     * @see User
     */
    public User getByEmailAndPassword(String email, String password) throws DAOException;

    /**
     * Returns information about user by email
     *
     * @param email - user's email.
     * @return information about user.
     * @throws DAOException if there was an error executing the query
     *                      in the database
     * @see User
     */
    public User getByEmail(String email) throws DAOException;

    /**
     * The function which changes the lock status to 'block'.
     *
     * @param userId - the user's id by which user will get blocked.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see User
     */
    public void block(long userId) throws DAOException;

    /**
     * The function which changes the lock status to 'unlocked'.
     *
     * @param userId - the user's id by which user will get unlocked.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see User
     */
    public void unlock(long userId) throws DAOException;

    /**
     * The function which returns the list of all users from database.
     *
     * @return list of all user
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see User
     */
    public List<User> getAll() throws DAOException;

    /**
     * The function which delete the user-librarian from database by book id.
     *
     * @param user - the user which will be deleted.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see User
     */
    public void delete(User user) throws DAOException;

}
