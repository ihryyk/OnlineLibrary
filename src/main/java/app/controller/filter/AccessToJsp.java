package app.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static app.controller.servlets.util.Constant.PageLocation.ERROR;

@WebFilter("/*")
public class AccessToJsp implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (req.getServletPath() != null && req.getServletPath().endsWith(".jsp")) {
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            resp.sendRedirect(ERROR);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

