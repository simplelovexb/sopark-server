package cn.suxiangbao.sopark.web.controller;

import cn.suxiangbao.sopark.http.BaseServletUtil;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static cn.suxiangbao.sopark.http.BaseServletUtil.*;

@Controller
public class LoginController {

    @RequestMapping(value = "/login"    )
    public void showLoginForm(HttpServletRequest request, HttpServletResponse response) {
        String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        String error = null;
        int retcode = FAILED;
        if (exceptionClassName!=null) {
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                error = "用户名/密码错误";
            } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                error = "用户名/密码错误";
            } else if (exceptionClassName != null) {
                error = "其他错误：" + exceptionClassName;
            }
        }else {
            error = "请先登录";
            retcode = UNLOGIN;
        }
        sendResponse(request,response,genMsgObj(retcode,error));
    }

}