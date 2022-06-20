import app.model.dao.exeption.DAOException;
import app.model.dao.exeption.ServiceException;
import app.model.entity.Book;
import app.model.enums.PassStatus;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class main {
    public static void main(String[] args) throws DAOException, IOException, SQLException, ServiceException {

       // UserDAO userDAO = DAOFactory.getUserDAO();
       // userDAO.registrationUser());

       // userDAO.updateUser(new User("vitya", "IHOR1231231@", "1231231"));
//        RoleDAO roleDAO = DAOFactory.getRoleDAO();
//        Role role = roleDAO.getRole(1);
           // AdministrationDAO administrationDAO = DAOFactory.getAdministrationDAO();
            //administrationDAO.createNewLibrarian(new User("artem","arRtem12311231@", "12314"));
           // administrationDAO.deleteLibrarian(new User("artem","arRtem12311231@", "12314"));
//        administrationDAO.deleteUser(new User("IHOR","IHOR123123@", "12314"));
//        System.out.println(userDAO.getAllUsers());
//        administrationDAO.unlockUser(new User("IHOR","IHOR1231231@", "12314"));
//        CatalogDAO catalogDAO = DAOFactory.getCatalogDAO();
//       catalogDAO.addCatalog(new Catalog("horror"));




//        BookDAO bookDAO = new BookDAOImpl();
//        subscription.setSubscriptionBooks(bookDAO.getAllBooks());
//
//        LibrarianDAO librarianDAO = new LibrarianDAOImpl();
//          BookDAO bookDAO = new BookDAOImpl();
       //   System.out.println(librarianDAO.getAllOrders("ua"));
         //System.out.println(librarianDAO.getAllSubscriptions("ua"));
       // System.out.println(bookDAO.getAllBooks("ua"));]
      //  System.out.println(bookDAO.getBooksByAuthor("музар", "ua"));
        // System.out.println(librarianDAO.getAllUsers());
         //UserDAO userDAO = new UserDAOImpl();
         //Order order = new Order(30, new User(6,"IHOR","IHOR1231231@", "12314"),bookDAO.getAllBooks("ua"), SUBSCRIPTION );
         //userDAO.createOrder(order);

//        AccessToReadingRoom access = new AccessToReadingRoom();
        //  UserDAO userDAO = DAOFactory.getUserDAO();
         // userDAO.registrationUser(new User("IR","IHOR1231123231@", "12314"));
//        access.setEndDate(new Timestamp(2001-01-01));
//        access.setStartDate(new Timestamp(2001-01-01));
//        access.setHallBooks(bookDAO.getAllBooks("ua"));
//        librarianDAO.createAccessToReadingRoom(access);

//        User user =  userDAO.loggedUser("123467@gmail.com","123");
//        System.out.println(user);
//        System.out.println(bookDAO.getBooksByTitle("чіпуха", "ua"));


//        Subscription subscription = new Subscription();
//        subscription.setStartDate(new Timestamp(2001-01-01));
//        subscription.setUser(new User(6,"IHOR","IHOR1231231@", "12314"));
//        subscription.setEndDate(new Timestamp(2001-01-01));
//        subscription.setSubscriptionBooks(bookDAO.getAllBooks("ua"));
////        librarianDAO.createSubscription(subscription);
//       LibrarianDAO librarianDAO = DAOFactory.getLibrarianDAO();
//        System.out.println(librarianDAO.getPassBooksById(26));
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?user=root&password=root");
//        PreparedStatement preparedStatement =  connection.prepareStatement(createBD());
//        preparedStatement.executeUpdate();

//        BookDAO bookDAO = DAOFactory.getBookDAO();
//        System.out.println(bookDAO.getAllForAdmin(0,9));
//        PassService passService = new PassService();
//        System.out.println(passService.getAll("eng"));
//        BookService bookService = new BookService();
//        bookService.delete(10);
//        LibrarianDAO librarianDAO = new LibrarianDAOImpl();
//        User user = new User();
//        user.setName("Ihor berezovskti");
//        user.setPassword("111111");
//        user.setEmailAddress("11111");
//        librarianDAO.createNew(user);
        PassStatus passStatus = PassStatus.ACTIVE;
        System.out.println( passStatus.toString());


    }


}
