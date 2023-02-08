package app.controller.servlets;

import app.model.dao.exeption.ServiceException;
import app.model.entity.Book;
import app.model.entity.Order;
import app.model.entity.Pass;
import app.model.enums.PassStatus;
import app.model.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import static app.controller.servlets.util.Constant.LanguageConstant.ENGLISH_LANGUAGE;
import static app.controller.servlets.util.Constant.OrderConstant.*;
import static app.controller.servlets.util.Constant.PageLocation.*;
import static app.controller.servlets.util.Constant.PassConstant.*;

/**
 * Functions which are used by librarian.
 *
 * @author Ihor Berezovskyi
 */
@WebServlet("/librarian/*")
public class LibrarianServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderService orderService = new OrderServiceImpl();
        PassService passService = new PassServiceImpl();
        List<Pass> passList = null;
        try {
            passService.updatePenalty();
            List<Order> orders = orderService.getAll(ENGLISH_LANGUAGE);
            passList = passService.getAll(ENGLISH_LANGUAGE);
            req.setAttribute(ORDER_LIST, orders);
        } catch (ServiceException e) {
            resp.sendRedirect(ERROR);
        }
        req.setAttribute(PASS_LIST, passList);
        req.getRequestDispatcher(LIBRARIAN_MAIN_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            switch (req.getPathInfo()) {
                case (APPLY_ORDER):
                    applyOrder(req, resp);
                    break;
                case (CANCEL_ORDER):
                    cancelOrder(req, resp);
                    break;
                case (FINISH_PASS):
                    finishPass(req, resp);
                    break;
            }
        } catch (ServiceException e) {
            resp.sendRedirect(ERROR);
        }
    }

    /**
     * Changes the pass status to 'finished'.
     *
     * @param req  {@link HttpServletRequest}.
     * @param resp {@link HttpServletResponse}.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @throws IOException      if I/O error occurs.
     */
    private void finishPass(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException {
        PassService passService = new PassServiceImpl();
        BookService bookService = new BookServiceImpl();
        passService.addPenalty(0, Long.parseLong(req.getParameter(FINISH_PASS_ID)));
        List<Book> passBooks = passService.getBooksById(Long.parseLong(req.getParameter(FINISH_PASS_ID)));
        for (int i = 0; i < passBooks.size(); i++) {
            bookService.changeNumber(passBooks.get(i).getId(), 1);
        }
        passService.finishPass(Long.parseLong(req.getParameter(FINISH_PASS_ID)));
        resp.sendRedirect(LIBRARIAN);
    }

    /**
     * Changes the order status to 'canceled'.
     *
     * @param req  {@link HttpServletRequest}.
     * @param resp {@link HttpServletResponse}.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @throws IOException      if I/O error occurs.
     */
    private void cancelOrder(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException {
        OrderService orderService = new OrderServiceImpl();
        orderService.cancel(Integer.parseInt(req.getParameter(CANCEL_ORDER_ID)));
        resp.sendRedirect(LIBRARIAN);

    }

    /**
     * Changes the order status to 'applied'.
     *
     * @param req  {@link HttpServletRequest}.
     * @param resp {@link HttpServletResponse}.
     * @throws ServiceException if there was an error executing the query
     *                          in the service
     * @throws IOException      if I/O error occurs.
     */
    private void applyOrder(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException {
        long orderId = Long.parseLong(req.getParameter(APPLY_ORDER_ID));
        Date starDate = Date.valueOf(req.getParameter(FINISH_START_DATE));
        Date endDate = Date.valueOf(req.getParameter(FINISH_END_DATE));
        OrderService orderService = new OrderServiceImpl();
        PassServiceImpl passServiceImpl = new PassServiceImpl();
        Pass pass = new Pass();
        Order order = new Order();
        order.setId(Long.parseLong(req.getParameter(APPLY_ORDER_ID)));
        pass.setOrder(order);
        pass.setPassStatus(PassStatus.ACTIVE);
        pass.setStartDate(starDate);
        pass.setEndDate(endDate);
        passServiceImpl.create(pass);
        orderService.submit(orderId);
        orderService.submit(Long.parseLong(req.getParameter(APPLY_ORDER_ID)));
        resp.sendRedirect(LIBRARIAN);
    }
}
