package app.controller.servlets;

import app.controller.servlets.util.StartPosition;
import app.model.dao.exeption.ServiceException;
import app.model.service.BookService;
import app.model.service.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static app.controller.servlets.util.Constant.BookConstant.BOOK_COUNT;
import static app.controller.servlets.util.Constant.BookConstant.BOOK_LIST;
import static app.controller.servlets.util.Constant.LanguageConstant.*;
import static app.controller.servlets.util.Constant.PageLocation.ERROR_PAGE;
import static app.controller.servlets.util.Constant.PageLocation.MAIN_PAGE;
import static app.controller.servlets.util.StartPosition.AMOUNT_BOOKS_ON_A_PAGE;


/**
 * This servlet represents index page in the library.
 */
@WebServlet("/")
public class Index extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        if (req.getSession().getAttribute(PAGE_LANGUAGE) == null) {
            req.getSession().setAttribute(PAGE_LANGUAGE, ENGLISH_LANGUAGE);
        }
        if (req.getParameter(CHOOSE_PAGE_LANGUAGE) != null) {
            req.getSession().setAttribute(PAGE_LANGUAGE, req.getParameter(CHOOSE_PAGE_LANGUAGE));
        }
        int start = StartPosition.getStartPosition(req);
        try {
            int count = bookService.getCount();
            req.setAttribute(BOOK_COUNT, count);
            req.setAttribute(BOOK_LIST, bookService.getAll((String) req.getSession().getAttribute(PAGE_LANGUAGE),
                    start, AMOUNT_BOOKS_ON_A_PAGE));
            req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
        } catch (ServiceException e) {
            resp.sendRedirect(ERROR_PAGE);
        }
    }
}

