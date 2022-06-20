package app.model.dao;

import app.model.dao.exeption.DAOException;
import app.model.entity.User;

import java.util.List;

/**
 * The interface defines methods for implementing different
 * activities with librarian
 *
 * @author Ihor Berezovskyi
 */
public interface LibrarianDAO {
    /**
     * The function which delete the user-librarian from database by book id.
     *
     * @param librarianId - id of user-librarian.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see User
     */
    void delete(long librarianId) throws DAOException;

    /**
     * The function which returns the list of all users-librarians from database.
     *
     * @return list of all user-librarian
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see User
     */
    List<User> getAll() throws DAOException;

    /**
     * The function which add a new user-librarian to database.
     *
     * @param librarian - the user-librarian which will be added to database.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see User
     */
    void createNew(User librarian) throws DAOException;
}
