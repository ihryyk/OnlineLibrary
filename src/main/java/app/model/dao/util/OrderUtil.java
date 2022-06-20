package app.model.dao.util;

import app.model.enums.BookOption;
import app.model.enums.OrderStatus;
import app.model.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

import static app.model.dao.util.SqlConstant.*;

/**
 * Used to store information about order from database.
 *
 * @author Ihor Berezovskyi
 */
public class OrderUtil {
    /**
     * The function which store information about order.
     * @param rs - result set.
     * @return  initialized order object.
     * @throws SQLException if there was database access error or other errors.
     */
    static public Order orderInitialization(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt(ORDER_ID));
        order.setArrivalDate(rs.getDate(ARRIVAL_DATE));
        order.setBookOption(BookOption.valueOf(rs.getString(ORDER_TYPE)));
        order.setUser(UserUtil.userInitialization(rs));
        order.setOrderSatus(OrderStatus.valueOf(rs.getString(ORDER_STATUS)));
        return order;
    }
}
