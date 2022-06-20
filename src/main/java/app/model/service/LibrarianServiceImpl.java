package app.model.service;

import app.controller.servlets.util.validate.ArgumentValidator;
import app.model.dao.DAOFactory;
import app.model.dao.LibrarianDAO;
import app.model.dao.exeption.DAOException;
import app.model.dao.exeption.ServiceException;
import app.model.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import java.util.List;

public class LibrarianServiceImpl implements LibrarianService {
    Logger logger = Logger.getLogger(LibrarianServiceImpl.class);
    LibrarianDAO librarianDAO = DAOFactory.getLibrarianDAO();

    /**
     * The function which add a new user-librarian to database.
     *
     * @param librarian - the user-librarian which will be added to database.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see User
     */
    public void createNew(User librarian) throws ServiceException {
        ArgumentValidator.checkForNull(librarian, "Not allow for a null user in createNew at userService class");
        try {
            String encryptPassword = DigestUtils.sha1Hex(librarian.getPassword());
            librarian.setPassword(encryptPassword);
            librarianDAO.createNew(librarian);
            logger.info("The 'createNew' function has been completed");
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in createNew at LibrarianService class", e);
        }
    }

    /**
     * The function which delete the user-librarian from database by book id.
     *
     * @param librarianId - id of user-librarian.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see User
     */
    public void delete(long librarianId) throws ServiceException {
        try {
            librarianDAO.delete(librarianId);
            logger.info("The 'delete' function has been completed");
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in delete at LibrarianService class", e);
        }
    }

    /**
     * The function which returns the list of all users-librarians from database.
     *
     * @return list of all user-librarian
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @see User
     */
    public List<User> getAll() throws ServiceException {
        try {
            List<User> librarians = librarianDAO.getAll();
            logger.info("The result of the 'getAll' function is obtained");
            return librarians;
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("exception in getAll at LibrarianService class", e);
        }
    }
}
