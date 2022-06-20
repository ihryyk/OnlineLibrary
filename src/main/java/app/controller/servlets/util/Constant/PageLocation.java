package app.controller.servlets.util.Constant;

public class PageLocation {
    public final static String ERROR = "/Error";
    public final static String ADMIN = "/admin";
    public final static String LIBRARIAN = "/librarian";
    public final static String LOGIN = "/login";
    public final static String REGISTRATION = "/registration";
    public final static String MAIN_PAGE = "/views/index.jsp";
    public final static String ERROR_PAGE = "/views/error.jsp";
    public final static String LOGIN_PAGE = "/views/login.jsp";
    public final static String REGISTRATION_PAGE = "/views/registration.jsp";

    //admin page
    public final static String ADMIN_MAIN_PAGE = "/views/adminIndex.jsp";
    public final static String CREATE_BOOK_PAGE = "/views/createBook.jsp";
    public final static String UPDATE_BOOK_PAGE = "/views/updateBook.jsp";
    public final static String CREATE_LIBRARIAN_PAGE = "/views/createLibrarian.jsp";

    //admin path info
    public final static String CREATE_BOOK = "/createBook";
    public final static String UPDATE_BOOK = "/updateBook";
    public final static String CREATE_LIBRARIAN = "/createLibrarian";
    public final static String DELETE_LIBRARIAN = "/deleteLibrarian";
    public final static String BLOCK_USER = "/blockUser";
    public final static String UNLOCK_USER = "/unlockUser";
    public final static String DELETE_BOOK = "/deleteBook";

    //librarian page
    public final static String LIBRARIAN_MAIN_PAGE = "/views/librarianIndex.jsp";

    //librarian path info
    public final static String APPLY_ORDER = "/applyOrder";
    public final static String CANCEL_ORDER = "/cancelOrder";
    public final static String FINISH_PASS = "/finishPass";

    public final static String USER_PASSES = "/myPasses";
    public final static String PROFILE_PAGE = "/views/myProfile.jsp";
    public final static String USER_ORDERS = "/myOrders";
    public final static String CREATE_ORDER = "/createOrder";
    public final static String USER_PROFILE = "/myProfile";

    public final static String ORDER_PAGE = "/views/order.jsp";
    public final static String USER_ORDER_PAGE = "/views/myOrders.jsp";
    public final static String USER_PASSE_PAGE = "/views/myPass.jsp";

    public final static String MY_PROFILE_URL = "/user/myProfile";
    public final static String CREATE_LIBRARIAN_URL = "/admin/createLibrarian";
    public final static String CREATE_BOOK_URL = "/admin/createBook";
}
