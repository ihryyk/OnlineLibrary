package app.controller.servlets;

import app.controller.servlets.util.Constant.PopUpsConstant;
import app.controller.servlets.util.SaveUser;
import app.controller.servlets.util.validate.Validator;
import app.model.dao.exeption.ServiceException;
import app.model.entity.User;
import app.model.service.UserService;
import app.model.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static app.controller.servlets.util.Constant.PageLocation.*;
import static app.controller.servlets.util.Constant.PopUpsConstant.POP_UPS;
import static app.controller.servlets.util.Constant.RoleConstant.LIBRARIAN_R;
import static app.controller.servlets.util.Constant.RoleConstant.USER_R;
import static app.controller.servlets.util.Constant.UserConstant.*;

/**
 * This servlet represents a log in page in the library.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();
        if (session != null) {
            req.getSession().removeAttribute(USER_PASSWORD);
            req.getSession().removeAttribute(USER_NAME);
            req.getSession().removeAttribute(USER_EMAIL_ADDRESS);
            req.getSession().removeAttribute(USER_ROLE);
            req.getSession().removeAttribute(USER_ID);
            req.getSession().removeAttribute(USER_LOCK_STATUS);
        }
        req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String password = req.getParameter(USER_PASSWORD);
        String email = req.getParameter(USER_EMAIL_ADDRESS);
        UserService userService = new UserServiceImpl();
        try {
            if (Validator.isValidPassword(password) && Validator.isValidEmail(email)) {
                User user = userService.getByEmailAndPassword(email, password);
                if (user != null) {
                    req.getSession().setAttribute(USER_PASSWORD, password);
                    SaveUser.saveUser(req, user);
                    if (req.getSession().getAttribute(USER_ROLE).equals(USER_R)) {
                        resp.sendRedirect("/");
                    } else if (req.getSession().getAttribute(USER_ROLE).equals(LIBRARIAN_R)) {
                        resp.sendRedirect(LIBRARIAN);
                    } else {
                        resp.sendRedirect(ADMIN);
                    }
                } else {
                    req.getSession().setAttribute(POP_UPS, PopUpsConstant.REGISTRATION);
                    resp.sendRedirect(LOGIN);
                }
            } else {
                req.getSession().setAttribute(POP_UPS, PopUpsConstant.VALID);
                resp.sendRedirect(LOGIN);
            }
        } catch (ServiceException e) {
            resp.sendRedirect(ERROR);
        }
    }
}
