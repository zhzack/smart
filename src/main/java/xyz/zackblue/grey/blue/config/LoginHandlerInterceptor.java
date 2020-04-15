package xyz.zackblue.grey.blue.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object LoginUser = request.getSession().getAttribute("loginUser");
        if (LoginUser == null) {
            request.setAttribute("msg", "未登录");
            request.getRequestDispatcher("/login.html").forward(request,response);
            return false;
        } else {
            return true;
        }
    }
}
