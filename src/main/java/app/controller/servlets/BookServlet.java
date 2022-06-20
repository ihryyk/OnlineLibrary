package app.controller.servlets;

import app.controller.servlets.util.StartPosition;
import app.model.dao.exeption.ServiceException;
import app.model.entity.Book;
import app.model.service.BookService;
import app.model.service.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static app.controller.servlets.util.Constant.BookConstant.*;
import static app.controller.servlets.util.Constant.LanguageConstant.PAGE_LANGUAGE;
import static app.controller.servlets.util.Constant.PageLocation.ERROR;
import static app.controller.servlets.util.Constant.PageLocation.MAIN_PAGE;
import static app.controller.servlets.util.StartPosition.AMOUNT_BOOKS_ON_A_PAGE;
import static app.model.dao.util.SqlConstant.BookRequestQuery.*;

/**
 * Functions with books.
 *
 * @author Ihor Berezovskyi
 */
@WebServlet("/bookList")
public class BookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            if (req.getParameter(SEARCH_BOOK_BY) != null) {
                switch (req.getParameter(SEARCH_BOOK_BY)) {
                    case BOOK_TITLE:
                        showBooksByTitle(req, resp);
                        break;
                    case BOOK_AUTHOR:
                        showBooksByAuthor(req, resp);
                        break;
                }
            }

            if (req.getParameter(GROUP_BOOK_BY) != null) {
                switch (req.getParameter(GROUP_BOOK_BY)) {
                    case BOOK_TITLE:
                        groupBy(req, resp, GROUP_BOOK_BY_TITLE);
                        break;
                    case BOOK_AUTHOR:
                        groupBy(req, resp, GROUP_BOOK_BY_AUTHOR);
                        break;
                    case BOOK_PUBLISHING_HOUSE:
                        groupBy(req, resp, GROUP_BOOK_BY_PUBLISHING_HOUSE);
                        break;
                    case BOOK_YEAR:
                        groupBy(req, resp, GROUP_BOOK_BY_YEAR);
                        break;
                }
            }
        } catch (ServiceException e) {
            resp.sendRedirect(ERROR);
        }
    }

    /**
     * Sort books by parameter in request.
     *
     * @param req     {@link HttpServletRequest}.
     * @param resp    {@link HttpServletResponse}.
     * @param request - the database query
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @throws IOException      if I/O error occurs.
     */
    private void groupBy(HttpServletRequest req, HttpServletResponse resp, String request) throws ServiceException, ServletException, IOException {
        BookServiceImpl bookService = new BookServiceImpl();
        int start = StartPosition.getStartPosition(req);
        List<Book> bookList = bookService.sortBy(request, (String) req.getSession().getAttribute(PAGE_LANGUAGE),
                start, AMOUNT_BOOKS_ON_A_PAGE);
        req.setAttribute(BOOK_LIST, bookList);
        req.setAttribute(BOOK_COUNT, bookService.getCount());
        req.setAttribute(GROUP_BOOK_BY, req.getParameter(GROUP_BOOK_BY));
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);

    }

    /**
     * Show books by author.
     *
     * @param req  {@link HttpServletRequest}.
     * @param resp {@link HttpServletResponse}.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @throws IOException      if I/O error occurs.
     */
    private void showBooksByAuthor(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        req.getSession().setAttribute(BOOK_AUTHOR, req.getParameter(SEARCH_PARAMETER));
        int start = StartPosition.getStartPosition(req);
        List<Book> bookList = bookService.getByAuthor((String) req.getSession().getAttribute(BOOK_AUTHOR),
                (String) req.getSession().getAttribute(PAGE_LANGUAGE), start, AMOUNT_BOOKS_ON_A_PAGE);
        req.setAttribute(BOOK_LIST, bookList);
        req.setAttribute(BOOK_COUNT, bookService.getCountBooksByAuthor((String) req.getSession().getAttribute(BOOK_AUTHOR)));
        req.setAttribute(SEARCH_BOOK_BY, BOOK_AUTHOR);
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);

    }

    /**
     * Show books by title.
     *
     * @param req  {@link HttpServletRequest}.
     * @param resp {@link HttpServletResponse}.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @throws IOException      if I/O error occurs.
     */
    private void showBooksByTitle(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        req.getSession().setAttribute(BOOK_TITLE, req.getParameter(SEARCH_PARAMETER));
        int start = StartPosition.getStartPosition(req);
        List<Book> bookList = bookService.getByTitle((String) req.getSession().getAttribute(BOOK_TITLE),
                (String) req.getSession().getAttribute(PAGE_LANGUAGE), start, AMOUNT_BOOKS_ON_A_PAGE);
        req.setAttribute(BOOK_LIST, bookList);
        req.setAttribute(BOOK_COUNT, bookService.getCountBooksByTitle((String) req.getSession().getAttribute(BOOK_TITLE)));
        req.setAttribute(SEARCH_BOOK_BY, BOOK_TITLE);
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}
