package cn.suxiangbao.sopark.web.controller;

import cn.suxiangbao.sopark.dao.UserDao;
import cn.suxiangbao.sopark.entity.User;
import cn.suxiangbao.sopark.http.BaseServletUtil;
import cn.suxiangbao.sopark.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static cn.suxiangbao.sopark.http.BaseServletUtil.*;

/**
 * Created by sxb on 2017/2/19.
 */
@RequestMapping("/anon")
@Controller
public class AnonController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/signUp")
    public void signUp(HttpServletRequest request, HttpServletResponse response, User user){
        if (user==null || user.getUsername() == null || user.getPassword()== null){
            sendResponse(request,response,genMsgObj(FAILED,"username or passowd could not be null"));
            return;
        }

        if (userService.findByUsername(request,response,user.getUsername())!=null){
            sendResponse(request,response,genMsgObj(USERNAME_EXISTED,"username had existed"));
            return;
        }
        userService.createUser(request,response,user);
        System.out.println("asdasdisah");
        sendResponse(request,response,genMsgObj(SUCCESS));
    }

    @RequestMapping(value = "/isLogin",method = RequestMethod.POST)
    public void isLogin(HttpServletRequest request, HttpServletResponse response){
        Boolean isLogin = SecurityUtils.getSubject().isAuthenticated();
        if (isLogin){
            sendResponse(request,response,genMsgObj(SUCCESS));
        }else{
            sendResponse(request,response,genMsgObj(FAILED));
        }

    }
}
