package app.model.dao.util;

import app.model.entity.Role;
import app.model.entity.User;
import app.model.enums.LockStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

import static app.model.dao.util.SqlConstant.*;

/**
 * Used to store information about user from database.
 *
 * @author Ihor Berezovskyi
 */
public class UserUtil {
    /**
     * The function which store information about user.
     * @param rs - result set.
     * @return  initialized user object.
     * @throws SQLException if there was database access error or other errors.
     */
    public static User userInitialization(ResultSet rs) throws SQLException {
        User user = new User();
        Role role = new Role();
        user.setId(rs.getInt(USER_ID));
        role.setId(rs.getInt(ROLE_ID));
        role.setName(rs.getString(ROLE_NAME));
        user.setName(rs.getString(USER_NAME));
        user.setEmailAddress(rs.getString(USER_EMAIL));
        user.setLockStatus(LockStatus.valueOf(rs.getString(LOCK_STATUS)));
        user.setRole(role);
        user.setPassword(rs.getString(USER_PASSWORD));
        return user;
    }
}
