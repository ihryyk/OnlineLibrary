package app.model.dao.impl;

import app.model.entity.*;
import app.model.enums.BookOption;
import app.model.enums.LockStatus;
import app.model.enums.OrderStatus;
import app.model.enums.PassStatus;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface TestData {


    BookTranslation ENGbt1 = new BookTranslation("Tiger hunters", new Language(1, "eng"), "Ivan Bahrianyi");
    BookTranslation ENGbt2 = new BookTranslation("Enejida", new Language(1, "eng"), "Ivan Kotlyarevsky");
    BookTranslation ENGbt3 = new BookTranslation("Kaidasheva family", new Language(1, "eng"), "Ivan Nechuy-Levytsky");
    BookTranslation ENGbt4 = new BookTranslation("I (Romance)", new Language(1, "eng"), "Mykola Khvylovy");
    BookTranslation ENGbt5 = new BookTranslation("Beyond pain: a novel-poem", new Language(1, "eng"), "Osyp Turyansʹkyy");
    BookTranslation ENGbt6 = new BookTranslation("Fata morgana", new Language(1, "eng"), "Mykhaylo Kotsyubynsʹkyy");
    BookTranslation ENGbt7 = new BookTranslation("Land (Native)", new Language(1, "eng"), "Olʹha Kobylyansʹka");
    BookTranslation ENGbt8 = new BookTranslation("Dovbush", new Language(1, "eng"), "Hnat Khotkevych");
    BookTranslation ENGbt9 = new BookTranslation("Klymko", new Language(1, "eng"), "Hryhir Tyutyunnyk");

    List<BookTranslation> ENGbkList1 = Arrays.asList(ENGbt1);
    Book b1 = new Book(1, 2001, "Folio", 20, ENGbkList1);
    List<BookTranslation> ENGbkList2 = Arrays.asList(ENGbt2);
    Book b2 = new Book(2, 2019, "Vivat", 19, ENGbkList2);
    List<BookTranslation> ENGbkList3 =Arrays.asList(ENGbt3);
    Book b3 = new Book(3, 2019, "Terra Incognita", 19, ENGbkList3);
    List<BookTranslation> ENGbkList4 = Arrays.asList(ENGbt4);
    Book b4 = new Book(4, 2019, "Folio", 19, ENGbkList4);
    List<BookTranslation> ENGbkList5 =Arrays.asList(ENGbt5);
    Book b5 = new Book(5, 2021, "Folio", 18, ENGbkList5);
    List<BookTranslation> ENGbkList6 = Arrays.asList(ENGbt6);
    Book b6 = new Book(6, 2021, "Folio", 20, ENGbkList6);
    List<BookTranslation> ENGbkList7 =Arrays.asList(ENGbt7);
    Book b7 = new Book(7, 2021, "Folio", 20, ENGbkList7);
    List<BookTranslation> ENGbkList8 = Arrays.asList(ENGbt8);
    Book b8 = new Book(8, 2021, "Apriori", 20, ENGbkList8);
    List<BookTranslation> ENGbkList9 =Arrays.asList(ENGbt9);
    Book b9 = new Book(9, 2017, "Знання", 20, ENGbkList9);
    List<Book> allBooks = new ArrayList<>(Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8, b9));

    BookTranslation UAbt1 = new BookTranslation("Тигролови", new Language(2, "ua"), "Іван Багряний");
    BookTranslation UAbt2 = new BookTranslation("Енеїда", new Language(2, "ua"), "Іван Котляревький");
    BookTranslation UAbt3 = new BookTranslation("Кайдашева сім'я", new Language(2, "ua"), "Іван Нечуй-Левицький");
    BookTranslation UAbt4 = new BookTranslation("Я (Романтика)", new Language(2, "ua"), "Микола Хвильовий");
    BookTranslation UAbt5 = new BookTranslation("Поза межами болю: повість-поема", new Language(2, "ua"), "Осип Турянський");
    BookTranslation UAbt6 = new BookTranslation("Fata morgana", new Language(2, "ua"), "Михайло Коцюбинський");
    BookTranslation UAbt7 = new BookTranslation("Земля (Рідне)", new Language(2, "ua"), "Ольга Кобилянська");
    BookTranslation UAbt8 = new BookTranslation("Довбуш", new Language(2, "ua"), "Гнат Хоткевич");
    BookTranslation UAbt9 = new BookTranslation("Климко", new Language(2, "ua"), "Григір Тютюнник");

    List<BookTranslation> UAbkList1 = Arrays.asList(UAbt1);
    List<BookTranslation> UAbkList2 = Arrays.asList(UAbt2);
    List<BookTranslation> UAbkList3 = Arrays.asList(UAbt3);
    List<BookTranslation> UAbkList4 = Arrays.asList(UAbt4);
    List<BookTranslation> UAbkList5 = Arrays.asList(UAbt5);
    List<BookTranslation> UAbkList6 = Arrays.asList(UAbt6);
    List<BookTranslation> UAbkList7 = Arrays.asList(UAbt7);
    List<BookTranslation> UAbkList8 =Arrays.asList(UAbt8);
    List<BookTranslation> UAbkList9 = Arrays.asList(UAbt9);
    List<BookTranslation> bkList1 = Arrays.asList(ENGbt1, UAbt1);

    Book book1 = new Book(1, 2001, "Folio", 20, bkList1);
    List<BookTranslation> bkList2 = new ArrayList<>(Arrays.asList(ENGbt2, UAbt2));
    Book book2 = new Book(2, 2019, "Vivat", 19, bkList2);
    List<BookTranslation> bkList3 = new ArrayList<>(Arrays.asList(ENGbt3, UAbt3));
    Book book3 = new Book(3, 2019, "Terra Incognita", 19, bkList3);
    List<BookTranslation> bkList4 = new ArrayList<>(Arrays.asList(ENGbt4, UAbt4));
    Book book4 = new Book(4, 2019, "Folio", 19, bkList4);
    List<BookTranslation> bkList5 = new ArrayList<>(Arrays.asList(ENGbt5, UAbt5));
    Book book5 = new Book(5, 2021, "Folio", 18, bkList5);
    List<BookTranslation> bkList6 = new ArrayList<>(Arrays.asList(ENGbt6, UAbt6));
    Book book6 = new Book(6, 2021, "Folio", 20, bkList6);
    List<BookTranslation> bkList7 = new ArrayList<>(Arrays.asList(ENGbt7, UAbt7));
    Book book7 = new Book(7, 2021, "Folio", 20, bkList7);
    List<BookTranslation> bkList8 = new ArrayList<>(Arrays.asList(ENGbt8, UAbt8));
    Book book8 = new Book(8, 2021, "Apriori", 20, bkList8);
    List<BookTranslation> bkList9 = new ArrayList<>(Arrays.asList(ENGbt9, UAbt9));
    Book book9 = new Book(9, 2017, "Знання", 20, bkList9);
    List<Book> allAdminBooks = new ArrayList<>(Arrays.asList(book1, book2, book3, book4, book5, book6, book7, book8, book9));

    Role libraryRole = new Role(3, "librarian");
    Role userRole = new Role(1, "user");
    Role adminRole = new Role(2, "admin");

    User librarian = new User(52, "librarian", "librarian@gmail.com",
            "7c4a8d09ca3762af61e59520943dc26494f8941b", libraryRole, LockStatus.UNLOCKED);
    User admin = new User(18, "adminnnn", "ihorr12r@gmail.com",
            "7c4a8d09ca3762af61e59520943dc26494f8941b", adminRole, LockStatus.UNLOCKED);
    User user = new User(14, "IhorBerezovskyi", "123467@gmail.com",
            "7c4a8d09ca3762af61e59520943dc26494f8941b", userRole, LockStatus.UNLOCKED);
    User blockUser = new User(14, "IhorBerezovskyi", "123467@gmail.com",
            "7c4a8d09ca3762af61e59520943dc26494f8941b", userRole, LockStatus.BLOCKED);

    Order order = new Order(50, user, Arrays.asList(b5), BookOption.SUBSCRIPTION, OrderStatus.SUBMITTED,
           Date.valueOf("2022-03-27"));
    Order orderForPass = new Order(50, user, Arrays.asList(b5), BookOption.SUBSCRIPTION, OrderStatus.SUBMITTED,
            Date.valueOf("2022-03-27"));
    Order cancelOrder = new Order(50, user, Arrays.asList(b5), BookOption.SUBSCRIPTION, OrderStatus.CANCELED,
            Date.valueOf("2022-03-27"));
    Order submittedOrder = new Order(50, user,Arrays.asList(b5), BookOption.SUBSCRIPTION, OrderStatus.SUBMITTED,
            Date.valueOf("2022-03-27"));
    Order inProgressingOrder = new Order(50, user, Arrays.asList(b5), BookOption.SUBSCRIPTION, OrderStatus.IN_PROCESSING,
            Date.valueOf("2022-03-27"));

    Pass pass = new Pass(35, Date.valueOf("2022-03-28"),
           Date.valueOf("2022-03-31"), PassStatus.ACTIVE, 0,orderForPass);
    Pass finishPass = new Pass(35,Date.valueOf("2022-03-28"),
            Date.valueOf("2022-03-31"), PassStatus.FINISH, 0,order);;

}

