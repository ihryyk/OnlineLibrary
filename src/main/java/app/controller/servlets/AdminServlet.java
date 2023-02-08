package app.controller.servlets;

import app.controller.servlets.util.Constant.PopUpsConstant;
import app.controller.servlets.util.StartPosition;
import app.controller.servlets.util.validate.Validator;
import app.model.dao.exeption.ServiceException;
import app.model.entity.Book;
import app.model.entity.BookTranslation;
import app.model.entity.Language;
import app.model.entity.User;
import app.model.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static app.controller.servlets.util.Constant.BookConstant.*;
import static app.controller.servlets.util.Constant.LanguageConstant.ENGLISH_LANGUAGE;
import static app.controller.servlets.util.Constant.LanguageConstant.UKRAINIAN_LANGUAGE;
import static app.controller.servlets.util.Constant.LibrarianConstant.LIBRARIAN_ID;
import static app.controller.servlets.util.Constant.LibrarianConstant.LIBRARIAN_LIST;
import static app.controller.servlets.util.Constant.PageLocation.*;
import static app.controller.servlets.util.Constant.PopUpsConstant.POP_UPS;
import static app.controller.servlets.util.Constant.UserConstant.*;
import static app.controller.servlets.util.StartPosition.AMOUNT_BOOKS_ON_A_PAGE;


/**
 * Functions which are used by admin.
 *
 * @author Ihor Berezovskyi
 */
@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookServiceImpl bookService = new BookServiceImpl();
        LibrarianServiceImpl librarianServiceImpl = new LibrarianServiceImpl();
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        int start = StartPosition.getStartPosition(req);
        try {
            if (req.getPathInfo() == null) {
                int count = bookService.getCount();
                req.setAttribute(BOOK_COUNT, count);
                req.setAttribute(BOOK_LIST, bookService.getAllForAdmin(start, AMOUNT_BOOKS_ON_A_PAGE));
                req.setAttribute(LIBRARIAN_LIST, librarianServiceImpl.getAll());
                req.setAttribute(USER_LIST, userServiceImpl.getAll());
                req.getRequestDispatcher(ADMIN_MAIN_PAGE).forward(req, resp);
            } else {
                switch (req.getPathInfo()) {
                    case (CREATE_BOOK):
                        req.getRequestDispatcher(CREATE_BOOK_PAGE).forward(req, resp);
                        break;
                    case (UPDATE_BOOK):
                        req.getRequestDispatcher(UPDATE_BOOK_PAGE).forward(req, resp);
                        break;
                    case (CREATE_LIBRARIAN):
                        req.getRequestDispatcher(CREATE_LIBRARIAN_PAGE).forward(req, resp);
                        break;
                }
            }
        } catch (ServiceException e) {
            resp.sendRedirect(ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            switch (req.getPathInfo()) {
                case (DELETE_LIBRARIAN):
                    deleteLibrarian(req, resp);
                    break;
                case (CREATE_LIBRARIAN):
                    createLibrarian(req, resp);
                    break;
                case (BLOCK_USER):
                    blockUser(req, resp);
                    break;
                case (UNLOCK_USER):
                    unlockUser(req, resp);
                    break;
                case (DELETE_BOOK):
                    deleteBook(req, resp);
                    break;
                case (UPDATE_BOOK):
                    updateBook(req, resp);
                    break;
                case (CREATE_BOOK):
                    createBook(req, resp);
                    break;
                default:
                    resp.sendRedirect(ADMIN);
            }
        } catch (ServiceException e) {
            resp.sendRedirect(ERROR);
        }
    }

    /**
     * Changes the lock status to 'unlocked'.
     *
     * @param req  {@link HttpServletRequest}.
     * @param resp {@link HttpServletResponse}.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @throws IOException      if I/O error occurs.
     */
    private void unlockUser(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException {
        UserService userService = new UserServiceImpl();
        userService.unlock(Long.parseLong(req.getParameter(USER_ID)));
        resp.sendRedirect(ADMIN);
    }

    /**
     * Changes the lock status to 'blocked'.
     *
     * @param req  {@link HttpServletRequest}.
     * @param resp {@link HttpServletResponse}.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @throws IOException      if I/O error occurs.
     */
    private void blockUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServiceException {
        UserService userService = new UserServiceImpl();
        userService.block(Long.parseLong(req.getParameter(USER_ID)));
        resp.sendRedirect(ADMIN);

    }

    /**
     * Admin registers a new librarian.
     *
     * @param req  {@link HttpServletRequest}.
     * @param resp {@link HttpServletResponse}.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @throws IOException      if I/O error occurs.
     */
    private void createLibrarian(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        LibrarianService librarianService = new LibrarianServiceImpl();
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        User librarian = new User();
        librarian.setName(req.getParameter(USER_NAME));
        librarian.setPassword(req.getParameter(USER_PASSWORD));
        librarian.setEmailAddress(req.getParameter(USER_EMAIL_ADDRESS));
        if (Validator.isValidPassword(librarian.getPassword()) && Validator.isValidName(librarian.getName())
                && Validator.isValidEmail(librarian.getEmailAddress())) {
            if (userServiceImpl.getByEmail(librarian.getEmailAddress()) != null) {
                req.getSession().setAttribute(POP_UPS, PopUpsConstant.LOGIN);
                resp.sendRedirect(CREATE_LIBRARIAN_URL);
            } else {
                librarianService.createNew(librarian);
                resp.sendRedirect(ADMIN);
            }
        } else {
            req.getSession().setAttribute(POP_UPS, PopUpsConstant.VALID);
            resp.sendRedirect(CREATE_LIBRARIAN_URL);
        }
    }

    /**
     * Admin delete a librarian.
     *
     * @param req  {@link HttpServletRequest}.
     * @param resp {@link HttpServletResponse}.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @throws IOException      if I/O error occurs.
     */
    private void deleteLibrarian(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServiceException {
        LibrarianService librarianService = new LibrarianServiceImpl();
        librarianService.delete(Long.parseLong(req.getParameter(LIBRARIAN_ID)));
        resp.sendRedirect(ADMIN);
    }

    /**
     * Admin create a new book.
     *
     * @param req  {@link HttpServletRequest}.
     * @param resp {@link HttpServletResponse}.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @throws IOException      if I/O error occurs.
     */
    private void createBook(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServiceException {
        BookService bookService = new BookServiceImpl();
        Book book = descriptionOfBook(req);
        if (book != null) {
            bookService.add(book);
            resp.sendRedirect(ADMIN);
        } else {
            req.getSession().setAttribute(POP_UPS, PopUpsConstant.VALID);
            resp.sendRedirect(CREATE_BOOK_URL);
        }
    }

    /**
     * Admin update the book.
     *
     * @param req  {@link HttpServletRequest}.
     * @param resp {@link HttpServletResponse}.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @throws IOException      if I/O error occurs.
     */
    private void updateBook(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServiceException {
        BookService bookService = new BookServiceImpl();
        Book book = descriptionOfBook(req);
        if (book != null) {
            book.setId(Long.parseLong(req.getParameter(BOOK_ID)));
            bookService.update(book);
            resp.sendRedirect(ADMIN);
        } else {
            req.getSession().setAttribute(POP_UPS, PopUpsConstant.VALID);
            resp.sendRedirect(ADMIN);
        }
    }

    /**
     * Admin delete the book.
     *
     * @param req  {@link HttpServletRequest}.
     * @param resp {@link HttpServletResponse}.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @throws IOException      if I/O error occurs.
     */
    private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServiceException {
        BookService bookService = new BookServiceImpl();
        bookService.delete(Long.parseLong(req.getParameter(BOOK_ID)));
        resp.sendRedirect(ADMIN);
    }

    /**
     * The function which stores information about book from request
     *
     * @param req {@link HttpServletRequest}.
     */
    private Book descriptionOfBook(HttpServletRequest req) {
        Book book = new Book();
        BookTranslation engBookTranslation = new BookTranslation();
        BookTranslation ukrBookTranslation = new BookTranslation();
        Language ukraineLanguage = new Language();
        ukraineLanguage.setName(UKRAINIAN_LANGUAGE);
        ukraineLanguage.setId(2);
        Language englishLanguage = new Language();
        englishLanguage.setName(ENGLISH_LANGUAGE);
        englishLanguage.setId(1);
        book.setPublishingHouse(req.getParameter(BOOK_PUBLISHING_HOUSE));
        book.setYear(Integer.parseInt((req.getParameter(BOOK_YEAR))));
        book.setNumber(Integer.parseInt(req.getParameter(BOOK_NUMBER)));
        engBookTranslation.setLanguage(englishLanguage);
        engBookTranslation.setTitle(req.getParameter(BOOK_ENG_TITLE));
        engBookTranslation.setAuthor(req.getParameter(BOOK_ENG_AUTHOR));
        ukrBookTranslation.setLanguage(ukraineLanguage);
        ukrBookTranslation.setTitle(req.getParameter(BOOK_UA_TITLE));
        ukrBookTranslation.setAuthor(req.getParameter(BOOK_UA_AUTHOR));
        List<BookTranslation> bookTranslationList = new ArrayList<>();
        bookTranslationList.add(engBookTranslation);
        bookTranslationList.add(ukrBookTranslation);
        book.setBookTranslations(bookTranslationList);
        if (Validator.isValidEng(book.getPublishingHouse()) && Validator.isValidYear(String.valueOf(book.getYear()))
                && Validator.isValidEng(engBookTranslation.getAuthor())
                && Validator.isValidEng(engBookTranslation.getTitle())
                && Validator.isValidUkr(ukrBookTranslation.getAuthor())
                && Validator.isValidUkr(ukrBookTranslation.getTitle())
                && Validator.isValidNumber(String.valueOf(book.getNumber()))) {
            return book;
        } else {
            return null;
        }
    }
}
