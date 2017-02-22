package cn.suxiangbao.sopark.web.controller;

import cn.suxiangbao.sopark.entity.User;
import cn.suxiangbao.sopark.entity.UserInfo;
import cn.suxiangbao.sopark.service.UserInfoService;
import cn.suxiangbao.sopark.web.bind.annotation.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static cn.suxiangbao.sopark.http.BaseServletUtil.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sxb on 2017/2/22.
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void updateUserInfo(HttpServletRequest request, HttpServletResponse response, @CurrentUser User user, UserInfo userInfo){
        if (user==null || userInfo == null){
            sendResponse(request,response,genMsgObj(USERNAME_EXISTED,"用户不存在"));
            return;
        }
        userInfoService.update(request,response,userInfo);
    }

    /**
     * 只能用于找用户自己的信息
     * @param request
     * @param response
     * @param user
     */
    @RequestMapping(value = "findSelfInfo")
    public void findUserInfoById(HttpServletRequest request,HttpServletResponse response,@CurrentUser User user){
        if (user == null){
            sendResponse(request,response,genMsgObj(USERNAME_EXISTED,"用户不存在"));
            return;
        }
        userInfoService.findByUid(request,response,user.getId());

    }

    @RequestMapping(value = "findByUsername")
    public void findUserInfoByUsername(HttpServletRequest request,HttpServletResponse response,@CurrentUser User user,String username){
        if (user == null){
            sendResponse(request,response,genMsgObj(FAILED,"参数错误"));
            return;
        }
        userInfoService.findByUsername(request,response,username);
    }

}
