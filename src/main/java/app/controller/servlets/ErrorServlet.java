package app.controller.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
