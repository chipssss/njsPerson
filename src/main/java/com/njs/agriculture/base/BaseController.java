package com.njs.agriculture.base;

import com.njs.agriculture.common.Const;
import com.njs.agriculture.pojo.User;

import javax.servlet.http.HttpSession;

/**
 * @author: chips
 * @date: 2020-01-12
 * @description:
 **/
public class BaseController {
    /* 根据session获取userId，如有，返回，如无，返回0 */
    protected int getUserIdBySession(HttpSession session) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            return user.getUserId();
        }
        return 0;
    }
}
