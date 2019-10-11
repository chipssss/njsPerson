package com.njs.agriculture.config;



import com.alibaba.fastjson.JSONObject;
import com.njs.agriculture.common.Const;
import com.njs.agriculture.common.ResponseCode;
import com.njs.agriculture.common.ServerResponse;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/28
 * @Description:
 */
public class AuthorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取访问的url
        String url = request.getRequestURI();

        if (url.indexOf("/portal/user/login") >= 0)
            return true;

        if (url.indexOf("/portal/user/checkNum") >= 0)
            return true;

        if (url.indexOf("/portal/user/apply") >= 0)
            return true;

        if (url.indexOf("/backend/user/register") >= 0)
            return true;

        if (url.indexOf("/backend/user/login") >= 0)
            return true;

        if(url.indexOf("/img/") >= 0)
            return true;

        if (url.indexOf("/portal/user/upload") >= 0)
            return true;

        if (url.indexOf("passwordChang") >= 0)
            return true;

        if(url.indexOf("portal/input/scanBarcode") >= 0)
            return true;

        //判断是否已登录
        HttpSession seesion = request.getSession();
        if (seesion.getAttribute(Const.CURRENT_ADMIN) != null)
            return true;

        if (seesion.getAttribute(Const.CURRENT_USER) != null)
            return true;

        /*若不满足以上，拦截*/
        //返回json数据
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSONObject.toJSONString(ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录！")));
        return false;
    }
}
