package app.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * The filter is designed to encoding parameters.
 *
 * @author Berezovskyi Ihor
 * @see Filter
 */
@WebFilter(urlPatterns = "/*", initParams = @WebInitParam(name = "encoding", value = "UTF-8"))
public class EncodingFilter implements Filter {
    String encoding;

    @Override
    public void init(FilterConfig filterConfig) {
        encoding = filterConfig.getInitParameter("encoding");
    }

    /**
     * The method encoding request.
     *
     * @param servletRequest  {@link ServletRequest}.
     * @param servletResponse {@link ServletResponse}.
     * @param filterChain     {@link FilterChain}
     * @throws ServletException if any inner exception in servlet occurs
     * @throws IOException      if I/O error occurs.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
