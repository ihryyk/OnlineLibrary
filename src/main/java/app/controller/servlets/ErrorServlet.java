package app.controller.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static app.controller.servlets.util.Constant.PageLocation.ERROR_PAGE;

/**
 * This servlet represents error page in the library.
 *
 * @author Ihor Berezovskyi
 */
@WebServlet("/Error")
public class ErrorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
    }
}
