package app.model.entity;

import app.model.enums.BookOption;
import app.model.enums.OrderStatus;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 * Used to store information about Order.
 *
 * @author Ihor Berezovskyi
 */
public class Order {
    /**
     * id of the Order.
     */
    private long id;
    /**
     * id of the Order.
     *
     * @see User
     */
    private User user;

    /**
     * books list of the Order.
     */
    private List<Book> orderBooks;
    /**
     * books option of the Order.
     *
     * @see BookOption
     */
    private BookOption bookOption;
    /**
     * order status of the Order.
     *
     * @see BookOption
     */
    private OrderStatus orderStatus;
    /**
     * start day of the Order.
     *
     * @see OrderStatus
     */
    private Date arrivalDate;


    public Order(long id, User user, List<Book> orderBooks, BookOption bookOption, OrderStatus orderStatus, Date arrivalDate) {
        this.id = id;
        this.user = user;
        this.orderBooks = orderBooks;
        this.bookOption = bookOption;
        this.orderStatus = orderStatus;
        this.arrivalDate = arrivalDate;
    }

    public Order() {
    }

    /**
     * Return the start the date of the last day the of the Order.
     *
     * @return the start the date of the last day the of the Order.
     */
    public Date getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Set the start the date of the last day of the Order.
     *
     * @param arrivalDate new arrival date of the Order
     */
    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * Return the id of the Order.
     *
     * @return the id of the Order.
     */
    public long getId() {
        return id;
    }

    /**
     * Set the id of the Order.
     *
     * @param id new id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Return the user of the Order.
     *
     * @return the id of the Order.
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the user of the Order.
     *
     * @param user new User.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Return the list of ordered books.
     *
     * @return the list of ordered books.
     */
    public List<Book> getOrderBooks() {
        return orderBooks;
    }

    /**
     * Set the list of ordered books.
     *
     * @param orderBooks new list of ordered books.
     */
    public void setOrderBooks(List<Book> orderBooks) {
        this.orderBooks = orderBooks;
    }

    /**
     * Return the book's option of the Order.
     *
     * @return the book's option of the Order.
     */
    public BookOption getBookOption() {
        return bookOption;
    }

    /**
     * Set the book's option of the Order.
     *
     * @param bookOption new book's option the Order.
     */
    public void setBookOption(BookOption bookOption) {
        this.bookOption = bookOption;
    }

    /**
     * Return the order status of the Order.
     *
     * @return the book's option of the Order.
     */
    public OrderStatus getOrderSatus() {
        return orderStatus;
    }

    /**
     * Set the order status of the Order.
     *
     * @param orderStatus new order status of the Order.
     */
    public void setOrderSatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Override {@link Object#equals(Object)}<br>
     * Check all fields of objects<br>
     *
     * @param o object to compare
     * @return Boolean equality of input and current objects
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(user, order.user) && Objects.equals(orderBooks, order.orderBooks) && bookOption == order.bookOption && orderStatus == order.orderStatus && Objects.equals(arrivalDate, order.arrivalDate);
    }


    /**
     * Override {@link Object#hashCode()}<br>
     *
     * @return integer hashCode of object/
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, user, orderBooks, bookOption, orderStatus, arrivalDate);
    }

    /**
     * Override {@link Object#toString()} ()}<br>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", orderBooks=" + orderBooks +
                ", bookOption=" + bookOption +
                ", orderSatus=" + orderStatus +
                ", arrivalDate=" + arrivalDate +
                '}';
    }
}
