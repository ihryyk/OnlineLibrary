package app.controller.servlets;

import app.controller.servlets.util.Constant.PopUpsConstant;
import app.controller.servlets.util.validate.Validator;
import app.model.dao.exeption.ServiceException;
import app.model.entity.Book;
import app.model.entity.Order;
import app.model.entity.Pass;
import app.model.entity.User;
import app.model.enums.BookOption;
import app.model.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static app.controller.servlets.util.Constant.BookConstant.*;
import static app.controller.servlets.util.Constant.LanguageConstant.PAGE_LANGUAGE;
import static app.controller.servlets.util.Constant.OrderConstant.*;
import static app.controller.servlets.util.Constant.PageLocation.*;
import static app.controller.servlets.util.Constant.PassConstant.PASS_LIST;
import static app.controller.servlets.util.Constant.PopUpsConstant.*;
import static app.controller.servlets.util.Constant.UserConstant.*;

/**
 * Functions which are used by user.
 *
 * @author Ihor Berezovskyi
 */
@WebServlet("/user/*")
public class UserServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            switch (req.getPathInfo()) {
                case (USER_PASSES):
                    myPasses(req, resp);
                    break;
                case (USER_ORDERS):
                    myOrders(req, resp);
                    break;
                case (USER_PROFILE):
                    req.getRequestDispatcher(PROFILE_PAGE).forward(req, resp);
                    break;
                case (CREATE_ORDER):
                    createOrderGet(req, resp);
                    break;
            }
        } catch (ServiceException e) {
            resp.sendRedirect(ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            switch (req.getPathInfo()) {
                case (USER_PROFILE):
                    myProfile(req, resp);
                    break;
                case (CREATE_ORDER):
                    createOrderPost(req, resp);
                    break;
            }
        } catch (ServiceException e) {
            resp.sendRedirect(ERROR);
        }
    }

    /**
     * Represents a createOrder page in the library.
     *
     * @param req  {@link HttpServletRequest}.
     * @param resp {@link HttpServletResponse}.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @throws IOException      if I/O error occurs.
     */
    private void createOrderGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        String[] deleteBooksId = req.getParameterValues(DELETE_BOOK_ID);
        List<Book> orderBooks = (List<Book>) req.getSession().getAttribute(BOOK_LIST);
        BookService bookService = new BookServiceImpl();
        if (deleteBooksId != null) {
            for (int i = 0; i < orderBooks.size(); i++) {
                for (int j = 0; j < deleteBooksId.length; j++) {
                    if (orderBooks.get(i).getId() == Long.parseLong(deleteBooksId[j])) {
                        orderBooks.remove(i);
                    }
                }
            }
            req.getSession().setAttribute(BOOK_LIST, orderBooks);
        } else {
            List<String> booksId = Arrays.asList((req.getParameterValues(BOOK_OPTION_ID)));
            List<Book> bookList = new ArrayList<>();
            for (int i = 0; i < booksId.size(); i++) {
                bookList.add(bookService.getById(Integer.parseInt(booksId.get(i)), (String) req.getSession().getAttribute(PAGE_LANGUAGE)));
            }
            req.getSession().setAttribute(BOOK_LIST, bookList);
        }
        req.getRequestDispatcher(ORDER_PAGE).forward(req, resp);
    }

    /**
     * Create order from order page.
     *
     * @param req  {@link HttpServletRequest}.
     * @param resp {@link HttpServletResponse}.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @throws IOException      if I/O error occurs.
     */
    private void createOrderPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServiceException {
        String password = (String) req.getSession().getAttribute(USER_PASSWORD);
        String email = (String) req.getSession().getAttribute(USER_EMAIL_ADDRESS);
        UserService userService = new UserServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        BookService bookService = new BookServiceImpl();
        Order order = new Order();
        order.setOrderBooks((List<Book>) req.getSession().getAttribute(BOOK_LIST));
        User user = userService.getByEmailAndPassword(email, password);
        for (int i = 0; i < order.getOrderBooks().size(); i++) {
            bookService.changeNumber(order.getOrderBooks().get(i).getId(), -1);
        }
        order.setUser(user);
        order.setBookOption(BookOption.valueOf(req.getParameter(ORDER_TYPE)));
        order.setArrivalDate(Date.valueOf(req.getParameter(ARRIVAL_DATE)));
        orderService.create(order);
        req.getSession().setAttribute(POP_UPS, ORDER);
        resp.sendRedirect("/");
    }

    /**
     * Represents a myProfile page in the library.
     *
     * @param req  {@link HttpServletRequest}.
     * @param resp {@link HttpServletResponse}.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @throws IOException      if I/O error occurs.
     */
    private void myProfile(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServiceException {
        UserService userService = new UserServiceImpl();
        User user = new User();
        String userName = req.getParameter(USER_NAME);
        String password = req.getParameter(USER_PASSWORD);
        String emailAddress = (String) req.getSession().getAttribute(USER_EMAIL_ADDRESS);
        if (Validator.isValidPassword(password) && Validator.isValidName(userName)) {
            req.getSession().setAttribute(USER_NAME, userName);
            req.getSession().setAttribute(USER_PASSWORD, password);
            req.getSession().setAttribute(USER_EMAIL_ADDRESS, emailAddress);
            user.setEmailAddress(emailAddress);
            user.setName(userName);
            user.setPassword(password);
            userService.update(user);
            req.getSession().setAttribute(POP_UPS, PROFILE);
            resp.sendRedirect("/");
        } else {
            req.getSession().setAttribute(POP_UPS, PopUpsConstant.VALID);
            resp.sendRedirect(MY_PROFILE_URL);
        }
    }

    /**
     * Represents a myOrders page in the library.
     *
     * @param req  {@link HttpServletRequest}.
     * @param resp {@link HttpServletResponse}.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @throws IOException      if I/O error occurs.
     */
    private void myOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        OrderService orderService = new OrderServiceImpl();
        List<Order> orderList = orderService.getAllByUserId((Long) req.getSession().getAttribute("userId"),
                (String) req.getSession().getAttribute(PAGE_LANGUAGE));
        req.setAttribute(ORDER_LIST, orderList);
        req.getRequestDispatcher(USER_ORDER_PAGE).forward(req, resp);
    }

    /**
     * Represents a myPasses page in the library.
     *
     * @param req  {@link HttpServletRequest}.
     * @param resp {@link HttpServletResponse}.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @throws IOException      if I/O error occurs.
     */
    private void myPasses(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        PassService passService = new PassServiceImpl();
        passService.updatePenalty();
        List<Pass> passList = passService.getAllByUserId((Long) req.getSession().getAttribute("userId"),
                (String) req.getSession().getAttribute(PAGE_LANGUAGE));
        req.setAttribute(PASS_LIST, passList);
        req.getRequestDispatcher(USER_PASSE_PAGE).forward(req, resp);
    }
}
