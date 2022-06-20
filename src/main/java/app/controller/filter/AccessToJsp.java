package app.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * The filter checks access to a jsp page.
 *
 * @author Ihor Berezovskyi
 * @see Filter
 */
import static app.controller.servlets.util.Constant.PageLocation.ERROR;
@WebFilter("/*")
public class AccessToJsp implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if(req.getServletPath()!=null && req.getServletPath().endsWith(".jsp")) {
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            resp.sendRedirect(ERROR);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

