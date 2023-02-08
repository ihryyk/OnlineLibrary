package app.controller.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


/**
 * The filter checks user access to a specific url-pattern.
 *
 * @author Ihor Berezovskyi
 * @see Filter
 */
@WebFilter({"/librarian/*", "/user/*", "/admin/*"})
public class AccessFilter implements Filter {

    /**
     * The method checks user access to a specific url-pattern.
     *
     * @param servletRequest  {@link ServletRequest}.
     * @param servletResponse {@link ServletResponse}.
     * @param filterChain     {@link FilterChain}
     * @throws ServletException if any inner exception in servlet occurs
     * @throws IOException      if I/O error occurs.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String userRole = (String) req.getSession().getAttribute("role");
        if (userRole != null) {
            switch (userRole) {
                case ("admin"):
                    if (!req.getServletPath().equals("/admin") || req.getServletPath().equals("")) {
                        resp.sendRedirect("/Error");
                        return;
                    }
                    break;
                case ("user"):
                    if (!req.getServletPath().equals("/user") || req.getServletPath().equals("")) {
                        resp.sendRedirect("/Error");
                        return;
                    }
                    break;
                case ("librarian"):
                    if (!req.getServletPath().equals("/librarian") || req.getServletPath().equals("")) {
                        resp.sendRedirect("/Error");
                        return;
                    }
                    break;
            }
        } else {
            if (req.getServletPath().equals("/user") || req.getServletPath().equals("/librarian") || req.getServletPath().equals("/admin")) {
                resp.sendRedirect("/Error");
                return;
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
