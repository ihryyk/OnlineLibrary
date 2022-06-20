package app.controller.servlets.util;

import javax.servlet.http.HttpServletRequest;



/**
 * util metods for servlet.
 */
public class StartPosition {
    public static final int AMOUNT_BOOKS_ON_A_PAGE = 5;
    public final static String PAGE_NUMBER = "page";
    /**
     * @param req {@link HttpServletRequest}.
     * @return start position for pagination
     */
    public static int getStartPosition(HttpServletRequest req) {
        int page = 0;
        if ((req.getParameter(PAGE_NUMBER) != null)) {
            page = Integer.parseInt(req.getParameter(PAGE_NUMBER));
        }
        return page * AMOUNT_BOOKS_ON_A_PAGE;
    }
}
