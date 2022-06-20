package app.controller.servlets.util;

import app.model.entity.User;

import javax.servlet.http.HttpServletRequest;

import static app.controller.servlets.util.Constant.UserConstant.*;
import static app.controller.servlets.util.Constant.UserConstant.USER_LOCK_STATUS;

public class SaveUser {
    static public void saveUser(HttpServletRequest req, User user){
        req.getSession().setAttribute(USER_NAME, user.getName());
        req.getSession().setAttribute(USER_EMAIL_ADDRESS, user.getEmailAddress());
        req.getSession().setAttribute(USER_ROLE, user.getRole().getName());
        req.getSession().setAttribute(USER_ID, user.getId());
        req.getSession().setAttribute(USER_LOCK_STATUS, user.getLockStatus());
    }
}
