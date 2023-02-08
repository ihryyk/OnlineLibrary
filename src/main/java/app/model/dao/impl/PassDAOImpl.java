package app.model.dao.impl;

import app.model.dao.PassDAO;
import app.model.dao.exeption.DAOException;
import app.model.dao.util.BookUtil;
import app.model.dao.util.ConnectionPool;
import app.model.dao.util.ConnectionUtil;
import app.model.dao.util.PassUtil;
import app.model.entity.Book;
import app.model.entity.Order;
import app.model.entity.Pass;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static app.model.dao.util.SqlConstant.BookRequestQuery.GET_ALL_ORDER_BOOKS;
import static app.model.dao.util.SqlConstant.BookRequestQuery.GET_ALL_PASS_BOOKS_BY_PASS_ID;
import static app.model.dao.util.SqlConstant.PassRequestQuery.*;
import static app.model.dao.util.SqlConstant.PenaltyRequestQuery.INSERT_NEW_PENALTY;

/**
 * Implement an interface that defines different activities with pass in database.
 *
 * @author Ihor Berezovskyi
 */
public class PassDAOImpl implements PassDAO {
    Logger logger = Logger.getLogger(PassDAOImpl.class);

    /**
     * The function which changes the pass status to 'finish'.
     *
     * @param passId - the id of pass.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Pass
     */
    @Override
    public void finishPass(long passId) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(FINISH_PASS)) {
            pr.setLong(1, passId);
            pr.executeUpdate();
            logger.info("The 'finishPass' function has been completed");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        }
    }

    /**
     * Returns the list of books which were added to order.
     *
     * @param passId - the id of pass.
     * @return - the list of books which were added to order
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Pass
     */
    @Override
    public List<Book> getBooksById(long passId) throws DAOException {
        List<Book> books = new ArrayList<>();
        ResultSet rs = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(GET_ALL_PASS_BOOKS_BY_PASS_ID)) {
            pr.setLong(1, passId);
            rs = pr.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("book_id"));
                book.setYear(rs.getInt("year"));
                book.setPublishingHouse(rs.getString("publishing_house"));
                book.setNumber(rs.getInt("number"));
                books.add(book);
            }
            logger.info("The result of the 'getBooksById' function is obtained");
            return books;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        } finally {
            ConnectionUtil.closeResultSet(rs);
        }
    }
    /**
     * The function which add a new pass to database.
     *
     * @param pass -  information about pass.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Pass
     * @see Order
     */
    @Override
    public void create(Pass pass) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement prCreate = connection.prepareStatement(CREATE_PASS);) {
            prCreate.setDate(1, pass.getStartDate());
            prCreate.setDate(2, pass.getEndDate());
            prCreate.setString(3, pass.getPassStatus().name());
            prCreate.setLong(4, pass.getOrder().getId());
            prCreate.executeUpdate();
            logger.info("The 'create' function has been completed");
        } catch (SQLException e) {
            throw new DAOException("Database query error", e);
        }
    }

    /**
     * Returns the list of pass for specified language.
     *
     * @param language - language for the book information.
     * @return - the list of pass for specified language.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Pass
     */
    @Override
    public List<Pass> getAll(String language) throws DAOException {
        List<Pass> passes = new ArrayList<>();
        ResultSet rs = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(SELECT_ALL_PASSES)) {
            rs = pr.executeQuery();
            while (rs.next()) {
                Pass pass = PassUtil.passInitialization(rs);
                pass.getOrder().setOrderBooks(BookUtil.getAllSelectedBooks(rs.getLong("id_order"),connection,GET_ALL_ORDER_BOOKS,language));
                passes.add(pass);
            }
            logger.info("The result of the 'getAll' function is obtained");
            return passes;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        } finally {
            ConnectionUtil.closeResultSet(rs);
        }
    }
    /**
     * Returns the list of pass for specified language by user id.
     *
     * @param language - language for the book information.
     * @param userID - id of user.
     * @return - the list of pass for specified language.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Pass
     */
    @Override
    public List<Pass> getAllByUserId(long userID, String language) throws DAOException {
        List<Pass> passes = new ArrayList<>();
        ResultSet rs = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pr = connection.prepareStatement(SELECT_ALL_PASSES_BY_USER_ID);) {
            pr.setLong(1, userID);
            rs = pr.executeQuery();
            while (rs.next()) {
                Pass pass = PassUtil.passInitialization(rs);
                pass.getOrder().setOrderBooks(BookUtil.getAllSelectedBooks(rs.getLong("id_order"),connection,GET_ALL_ORDER_BOOKS,language));
                passes.add(pass);
            }
            logger.info("The result of the 'getAllByUserId' function is obtained");
            return passes;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        } finally {
            ConnectionUtil.closeResultSet(rs);
        }
    }

    /**
     * The function which adds penalty to the pass.
     *
     * @param penalty - the amount of the fine to be added to the pass.
     * @throws DAOException if there was an error executing the query
     *                      in the database.
     * @see Pass
     */
    @Override
    public void addPenalty(int penalty, long passId) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_PENALTY)) {
            preparedStatement.setInt(1, penalty);
            preparedStatement.setLong(2,passId);
            preparedStatement.executeUpdate();
            logger.info("The 'add' function has been completed");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database query error", e);
        }
    }

}
