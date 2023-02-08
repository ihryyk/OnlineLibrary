package app.model.dao.util;

import app.model.entity.Pass;
import app.model.enums.PassStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

import static app.model.dao.util.SqlConstant.*;

/**
 * Used to store information about pass from database.
 *
 * @author Ihor Berezovskyi
 */
public class PassUtil {
    /**
     * The function which store information about pass.
     * @param rs - result set.
     * @return  initialized pass object.
     * @throws SQLException if there was database access error or other errors.
     */
    static public Pass passInitialization(ResultSet rs) throws SQLException {
        Pass pass = new Pass();
        pass.setId(rs.getInt(PASS_ID));
        pass.setOrder(OrderUtil.orderInitialization(rs));
        pass.setEndDate(rs.getDate(END_DATE));
        pass.setStartDate(rs.getDate(START_DATE));
        pass.setPassStatus(PassStatus.valueOf(rs.getString(PASS_STATUS)));
        pass.setPenalty(rs.getInt(PENALTY));
        return pass;
    }
}
