package app.model.dao.util;

public class SqlConstant {
    //book constant
    public static final String BOOK_ID = "book_id";
    public static final String BOOK_YEAR = "year";
    public static final String PUBLISHING_HOUSE = "publishing_house";
    public static final String BOOK_NUMBER = "number";
    public static final String LANGUAGE_ID = "language_id";
    public static final String LANGUAGE_NAME = "name_language";
    public static final String TITLE = "title";
    public static final String AUTHOR = "author";
    public static final String DELETED = "deleted";

    //order constant
    public static final String ORDER_ID = "order_id";
    public static final String ARRIVAL_DATE = "arrival_date";
    public static final String ORDER_TYPE = "order_type";
    public static final String ORDER_STATUS = "order_status";

    //pass constant
    public static final String PASS_ID = "pass_id";
    public static final String END_DATE = "end_date";
    public static final String START_DATE = "start_date";
    public static final String PASS_STATUS = "pass_status";
    public static final String PENALTY = "penalty";

    //user constant
    public static final String USER_NAME = "user_name";
    public static final String USER_ID = "user_id";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String ROLE_ID = "role_id";
    public static final String ROLE_NAME = "name";
    public static final String LOCK_STATUS = "lock_status";


    public static class UserRequestQuery {
        public static final String INSERT_NEW_USER = "INSERT INTO user (user_name, email, password, id_role) VALUES (?,?,?,1)";
        public static final String UPDATE_USER = "UPDATE USER SET user_name=?, password =? WHERE email = ?";
        public static final String SELECT_ALL_USERS = "SELECT *  FROM user as us INNER JOIN role WHERE role_id = us.id_role";
        public static final String DELETE_USER = "DELETE FROM user WHERE email = ?";
        public static final String BLOCK_USER = "UPDATE USER SET lock_status = 'BLOCKED' WHERE user_id =?";
        public static final String UNLOCK_USER = "UPDATE USER SET lock_status = 'UNLOCKED' WHERE  user_id =?";
        public static final String IS_USER_LOGGED = "SELECT * FROM user INNER JOIN role on user.id_role = role_id where email = ? AND password = ?";
        public static final String GET_USER_BY_EMAIL = "SELECT * FROM user INNER JOIN role on user.id_role = role_id where email = ?";

    }

    public static class BookRequestQuery {
        public static final String ADD_NEW_BOOK = "INSERT INTO book (year, publishing_house, number) values (?,?,?)";
        public static final String UPDATE_BOOK = "UPDATE book SET year = ?, publishing_house = ?, number=? WHERE book_id=?";
        public static final String SELECT_BOOK = "SELECT * FROM book INNER JOIN translation_book tb on book.book_id = tb.id_book INNER JOIN language l on tb.id_language = l.language_id WHERE book_id = ? AND name_language = ?";
        public static final String GET_ALL_BOOKS = "SELECT * FROM book INNER JOIN translation_book tb on book.book_id = tb.id_book INNER JOIN language l on tb.id_language = l.language_id WHERE name_language = ? AND book.deleted='0' LIMIT ";
        public static final String ADD_BOOK_TO_ORDER = "INSERT INTO order_book (id_order, id_book) VALUES (?,?)";
        public static final String GET_ALL_BOOKS_ADMIN = "SELECT * FROM book WHERE book.deleted='0' LIMIT ";
        public static final String GET_ALL_BOOKS_BY_AUTHOR = "SELECT * FROM book INNER JOIN translation_book tb on book.book_id = tb.id_book INNER JOIN language l on tb.id_language = l.language_id WHERE author RLIKE ?  AND l.name_language =? AND book.deleted='0' LIMIT ";
        public static final String GET_ALL_BOOKS_BY_TITLE = "SELECT * FROM book INNER JOIN translation_book tb on book.book_id = tb.id_book INNER JOIN language l on tb.id_language = l.language_id WHERE title RLIKE ?  AND l.name_language =? AND book.deleted='0' LIMIT ";
        public static final String COUNT_BOOK_BY_TITLE = "SELECT count(*) FROM book INNER JOIN translation_book tb on book.book_id = tb.id_book INNER JOIN language l on tb.id_language = l.language_id WHERE l.name_language='eng'AND title=? AND book.deleted='0'";
        public static final String COUNT_BOOK_BY_AUTHOR = "SELECT count(*) FROM book INNER JOIN translation_book tb on book.book_id = tb.id_book INNER JOIN language l on tb.id_language = l.language_id WHERE l.name_language='eng'AND author=? AND book.deleted='0'";
        public static final String GROUP_BOOK_BY_TITLE = "SELECT * FROM book INNER JOIN translation_book tb on book.book_id = tb.id_book INNER JOIN language l on tb.id_language = l.language_id WHERE name_language = ? AND book.deleted='0' ORDER BY title COLLATE utf8_unicode_ci LIMIT ";
        public static final String GROUP_BOOK_BY_AUTHOR = "SELECT * FROM book INNER JOIN translation_book tb on book.book_id = tb.id_book INNER JOIN language l on tb.id_language = l.language_id WHERE name_language = ? AND book.deleted='0' ORDER BY author COLLATE  utf8_unicode_ci LIMIT ";
        public static final String GROUP_BOOK_BY_PUBLISHING_HOUSE = "SELECT * FROM book INNER JOIN translation_book tb on book.book_id = tb.id_book INNER JOIN language l on tb.id_language = l.language_id WHERE name_language = ? AND book.deleted='0' ORDER BY publishing_house LIMIT ";
        public static final String GROUP_BOOK_BY_YEAR = "SELECT * FROM book INNER JOIN translation_book tb on book.book_id = tb.id_book INNER JOIN language l on tb.id_language = l.language_id WHERE name_language = ? AND book.deleted='0' ORDER BY year LIMIT ";
        //        public static final String DELETE_BOOK = "DELETE FROM book WHERE book_id = ?";
        public static final String DELETE_BOOK = "UPDATE book SET deleted='1' WHERE book_id = ?";
        public static final String UPDATE_BOOK_TRANSLATION = "UPDATE translation_book inner join language l on translation_book.id_language = l.language_id SET title = ?, author = ? where id_book =? and l.name_language = ?;";
        public static final String INSERT_TRANSLATION_FOR_BOOK = "INSERT INTO translation_book (ID_LANGUAGE, ID_BOOK, TITLE, AUTHOR) VALUES (?,?,?,?)";
        public static final String UPDATE_NUMBER_IN_BOOK = "UPDATE book SET `number`= `number`+ ? where book_id=?";
        public static final String GET_ALL_ORDER_BOOKS = "SELECT * FROM order_book INNER JOIN book on order_book.id_book = book_id INNER JOIN translation_book tb on book.book_id = tb.id_book INNER JOIN language l on tb.id_language = l.language_id WHERE id_order =? AND l.name_language = ?";
        public static final String GET_ALL_PASS_BOOKS_BY_PASS_ID = "SELECT * FROM order_book inner join pass p on order_book.id_order = p.id_order inner join book b on order_book.id_book = b.book_id  WHERE pass_id = ?";

    }

    public static class PassRequestQuery {
        public static final String SELECT_ALL_PASSES_BY_USER_ID = "SELECT * FROM pass INNER JOIN `order` o on o.order_id = pass.id_order INNER JOIN user u on o.id_user = u.user_id INNER JOIN role r on u.id_role = r.role_id WHERE user_id  = ?";
        public static final String FINISH_PASS = "UPDATE pass SET pass_status = 'FINISH' WHERE pass_id = ?";
        public static final String CREATE_PASS = "INSERT INTO pass (start_date, end_date,pass_status, id_order) VALUES (?,?,?,?)";
        public static final String SELECT_ALL_PASSES = "SELECT * FROM pass INNER JOIN `order` o on o.order_id = pass.id_order INNER JOIN user u on o.id_user = u.user_id INNER JOIN role r on u.id_role = r.role_id";
    }

    public static class LibrarianRequestQuery {
        public static final String GET_ALL_LIBRARIANS = "SELECT * FROM user INNER JOIN role r on user.id_role = r.role_id WHERE r.name = 'librarian'";
        public static final String DELETE_LIBRARIAN = "DELETE FROM user WHERE user_id = ?";
        public static final String INSERT_NEW_LIBRARIAN = "INSERT INTO user (user_name, email, password, id_role) VALUES (?,?,?,3)";
    }

    public static class OrderRequestQuery {
        public static final String SELECT_ALL_ORDER = "SELECT * FROM `order` INNER JOIN user u on `order`.id_user = u.user_id INNER JOIN role on u.id_role = role_id";
        public static final String SELECT_ALL_ORDER_BI_ID = "SELECT * FROM `order` INNER JOIN user u on `order`.id_user = u.user_id INNER JOIN role on u.id_role = role_id WHERE order_id =?";
        public static final String CANCEL_ORDER = "UPDATE `order` SET order_status = 'CANCELED' WHERE order_id = ?";
        public static final String CREATE_ORDER = "INSERT INTO `order` (id_user, order_type, order_status, arrival_date) VALUES (?,?,?,?)";
        public static final String CHANGE_ORDER_STATE = "UPDATE `order` SET order_status = ? WHERE order_id = ?";
        public static final String SELECT_ALL_ORDER_BY_USER_ID = "SELECT * FROM  `order` INNER JOIN user u on order.id_user = u.user_id INNER JOIN role on u.id_role = role_id WHERE order.id_user = ?";
    }

    public static class PenaltyRequestQuery {
        public static final String INSERT_NEW_PENALTY = "UPDATE pass SET penalty=?  WHERE pass_id=?";

    }
}