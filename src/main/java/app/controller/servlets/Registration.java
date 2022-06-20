package app.controller.servlets;

import app.controller.servlets.util.Constant.PopUpsConstant;
import app.controller.servlets.util.SaveUser;
import app.model.dao.exeption.ServiceException;
import app.controller.servlets.util.validate.Validator;
import app.model.entity.User;
import app.model.service.UserService;
import app.model.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static app.controller.servlets.util.Constant.PageLocation.*;
import static app.controller.servlets.util.Constant.PopUpsConstant.POP_UPS;
import static app.controller.servlets.util.Constant.UserConstant.*;

/**
 * This servlet represents a registation page in the library.
 */
@WebServlet("/registration")
public class Registration extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserService userService = new UserServiceImpl();
        User user = new User();
        String userName = req.getParameter(USER_NAME);
        String userPassword = req.getParameter(USER_PASSWORD);
        String userEmailAddress = req.getParameter(USER_EMAIL_ADDRESS);
        user.setPassword(userPassword);
        user.setEmailAddress(userEmailAddress);
        user.setName(userName);
        if (Validator.isValidPassword(userPassword) && Validator.isValidName(userName)
                && Validator.isValidEmail(userEmailAddress)) {
            try {
                if (userService.getByEmail(user.getEmailAddress()) != null) {
                    req.getSession().setAttribute(POP_UPS, PopUpsConstant.LOGIN);
                    resp.sendRedirect(REGISTRATION);
                } else {
                    userService.add(user);
                    user = userService.getByEmail(user.getEmailAddress());
                    req.getSession().setAttribute(USER_PASSWORD, userPassword);
                    SaveUser.saveUser(req,user);
                    req.getSession().setAttribute(POP_UPS, PopUpsConstant.REGISTRATION);
                    resp.sendRedirect("/");
                }
            } catch (ServiceException e) {
                resp.sendRedirect(ERROR);
            }
        }else {
            req.getSession().setAttribute(POP_UPS, PopUpsConstant.VALID);
            resp.sendRedirect(REGISTRATION);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(REGISTRATION_PAGE).forward(req, resp);
    }
}