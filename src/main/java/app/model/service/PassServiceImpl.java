package app.model.service;

import app.controller.servlets.util.validate.ArgumentValidator;
import app.model.dao.DAOFactory;
import app.model.dao.PassDAO;
import app.model.dao.exeption.DAOException;
import app.model.dao.exeption.ServiceException;
import app.model.entity.Book;
import app.model.entity.Order;
import app.model.entity.Pass;
import app.model.enums.PassStatus;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


public class PassServiceImpl implements PassService {
    public static final int PENALTY = 20;
    Logger logger = Logger.getLogger(PassServiceImpl.class);
    PassDAO passDAO = DAOFactory.getPassDAO();

    /**
     * The function which changes the pass status to 'finish'.
     *
     * @param passId - the id of pass.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see Pass
     */
    public synchronized void finishPass(long passId) throws ServiceException {
        try {
            passDAO.finishPass(passId);
            logger.info("The 'finishPass' function has been completed");
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in finishPass at passService class", e);
        }
    }

    /**
     * Returns the list of books which were added to order.
     *
     * @param passId - the id of pass.
     * @return - the list of books which were added to order
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see Pass
     */
    public List<Book> getBooksById(long passId) throws ServiceException {
        try {
            List<Book> books = passDAO.getBooksById(passId);
            logger.info("The result of the 'getBooksById' function is obtained");
            return books;
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in getBooksById at passService class", e);
        }
    }

    /**
     * The function which add a new pass to database.
     *
     * @param pass - safes information about new Pass.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see Pass
     * @see Order
     */
    public void create(Pass pass) throws ServiceException {
        ArgumentValidator.checkForNull(pass, "Not allow for a null order in create at passService class");
        try {
            passDAO.create(pass);
            logger.info("The 'create' function has been completed");
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in create at passService class", e);
        }
    }

    /**
     * Returns the list of pass for specified language.
     *
     * @param language - language for the book information.
     * @return - the list of pass for specified language.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see Pass
     */
    public List<Pass> getAll(String language) throws ServiceException {
        try {
            List<Pass> passes = passDAO.getAll(language);
            logger.info("The result of the 'getAll' function is obtained");
            return passes;
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in getAll at passService class", e);
        }
    }

    /**
     * Returns the list of pass for specified language by user id.
     *
     * @param language - language for the book information.
     * @param userID   - id of user.
     * @return - the list of pass for specified language.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see Pass
     */
    public List<Pass> getAllByUserId(long userID, String language) throws ServiceException {
        try {
            List<Pass> passes = passDAO.getAllByUserId(userID, language);
            logger.info("The result of the 'getAllByUserId' function is obtained");
            return passes;
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in getAllByUserId at passService class", e);
        }
    }

    /**
     * The function checks the expiry date of the pass and charges a penatly in case of expired one. .
     *
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see Pass
     */
    public void updatePenalty() throws ServiceException {

        try {
            List<Pass> passList = passDAO.getAll("eng");
            for (Pass pass : passList) {
                LocalDate endDateLc = pass.getEndDate().toLocalDate();
                LocalDate now = LocalDate.now();
                int days = (int) ChronoUnit.DAYS.between(endDateLc, now);
                if (pass.getPassStatus() == PassStatus.ACTIVE && days > 0) {
                    int penalty = pass.getOrder().getOrderBooks().size() * PENALTY * days;
                    passDAO.addPenalty(penalty, pass.getId());
                }
            }
            logger.info("The 'add' function has been completed");
        } catch (DAOException e) {
            throw new ServiceException("exception in add at PenaltyService class", e);
        }
    }

    /**
     * The function which adds penalty to the pass.
     *
     * @param penalty - the amount of the fine to be added to the pass.
     * @param passId  - id of pass
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see Pass
     */
    public void addPenalty(int penalty, long passId) throws ServiceException {
        try {
            passDAO.addPenalty(penalty, passId);
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in addPenalty at penaltyService class", e);
        }
    }
}
