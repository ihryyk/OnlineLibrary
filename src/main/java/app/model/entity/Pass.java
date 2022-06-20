package app.model.entity;

import app.model.enums.BookOption;
import app.model.enums.PassStatus;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 * Used to store information about Pass.
 *
 * @author Ihor Berezovskyi
 */
public class Pass {
    /**
     * id of the Pass.
     */
    private long id;
    /**
     * start date of the Pass.
     */
    private Date startDate;
    /**
     * end date of the Pass.
     */
    private Date endDate;
    /**
     * pass status of the Pass.
     */
    private PassStatus passStatus;
    /**
     * penalty of the Pass.
     */
    private int penalty;

    /**
     * order of the Pass.
     * @see Order
     */
    private Order order;

    public Pass() {
    }

    public Pass(long id, Date startDate, Date endDate, PassStatus passStatus, int penalty, Order order) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.passStatus = passStatus;
        this.penalty = penalty;
        this.order = order;
    }

    /**
     * Return the penalty of the Pass.
     *
     * @return the penalty of the Pass.
     */
    public int getPenalty() {
        return penalty;
    }

    /**
     * Set the penalty of the Pass.
     *
     * @param penalty new penalty.
     */
    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    /**
     * Return the pass status of the Pass.
     *
     * @return the pass status of the Pass.
     */
    public PassStatus getPassStatus() {
        return passStatus;
    }

    /**
     * Set  the pass satus of the Pass.
     *
     * @param passStatus new pass status.
     */
    public void setPassStatus(PassStatus passStatus) {
        this.passStatus = passStatus;
    }

    /**
     * Return the id of the Pass.
     *
     * @return the id of the Pass.
     */
    public long getId() {
        return id;
    }

    /**
     * Set the id of the Pass.
     *
     * @param id new id.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Return the start date of the Pass.
     *
     * @return the start date of the Pass.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Set the start date of the Pass.
     *
     * @param startDate new start date.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Return end date of the Pass.
     *
     * @return the end date of the Pass.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Set end date of the Pass.
     *
     * @param endDate new end date.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Return the order of the Pass.
     *
     * @return the order of the Pass.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Set the order of the Pass.
     *
     * @param order new order of the Pass.
     */
    public void setOrder(Order order) {
        this.order = order;
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
        Pass pass = (Pass) o;
        return id == pass.id && penalty == pass.penalty && Objects.equals(startDate, pass.startDate) && Objects.equals(endDate, pass.endDate) && passStatus == pass.passStatus && Objects.equals(order, pass.order);
    }




    /**
     * Override {@link Object#hashCode()}<br>
     *
     * @return integer hashCode of object/
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, passStatus, penalty, order);
    }

    /**
     * Override {@link Object#toString()} ()}<br>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "Pass{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", passStatus=" + passStatus +
                ", penalty=" + penalty +
                ", order=" + order +
                '}';
    }
}
