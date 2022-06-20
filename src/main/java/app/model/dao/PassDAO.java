package app.model.dao;

import app.model.dao.exeption.DAOException;
import app.model.entity.Book;
import app.model.entity.Order;
import app.model.entity.Pass;

import java.util.List;

/**
 * The interface defines methods for implementing different
 * activities with pass
 *
 * @author Ihor Berezovskyi
 */
public interface PassDAO {
    /**
     * Returns the list of pass for specified language by user id.
     *
     * @param language - language for the book information.
     * @param userID   - id of user.
     * @return - the list of pass for specified language.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Pass
     */
    public List<Pass> getAllByUserId(long userID, String language) throws DAOException;

    /**
     * The function which add a new pass to database.
     *
     * @param pass -  information about pass.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Pass
     * @see Order
     */
    public void create(Pass pass) throws DAOException;

    /**
     * The function which changes the pass status to 'finish'.
     *
     * @param passId - the id of pass.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Pass
     */
    public void finishPass(long passId) throws DAOException;

    /**
     * Returns the list of books which were added to order.
     *
     * @param passId - the id of pass.
     * @return - the list of books which were added to order
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Pass
     */
    public List<Book> getBooksById(long passId) throws DAOException;

    /**
     * Returns the list of pass for specified language.
     *
     * @param language - language for the book information.
     * @return - the list of pass for specified language.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Pass
     */
    public List<Pass> getAll(String language) throws DAOException;

    /**
     * The function which adds penalty to the pass.
     *
     * @param penalty - the amount of the fine to be added to the pass.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Pass
     */
    public void addPenalty(int penalty, long passId) throws DAOException;
}
