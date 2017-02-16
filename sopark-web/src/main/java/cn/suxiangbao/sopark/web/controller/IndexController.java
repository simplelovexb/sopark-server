package cn.suxiangbao.sopark.web.controller;

import cn.suxiangbao.sopark.entity.GeoPoint;
import cn.suxiangbao.sopark.entity.User;
import cn.suxiangbao.sopark.service.UserService;
import cn.suxiangbao.sopark.web.bind.annotation.CurrentUser;
import cn.suxiangbao.sopark.http.BaseServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cn.suxiangbao.sopark.http.BaseServletUtil.SUCCESS;
import static cn.suxiangbao.sopark.http.BaseServletUtil.genMsgObj;
import static cn.suxiangbao.sopark.http.BaseServletUtil.sendResponse;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getNearData"    )
    public void getNearData(HttpServletRequest request, HttpServletResponse response , @CurrentUser  User user,
                            @RequestParam(required = false) Double longitude,@RequestParam(required = false) Double latitude) {
        System.out.println(user);
        sendResponse(request,response,genMsgObj(SUCCESS,"getNearData",user));
    }

    @RequestMapping(value = "/getTemplateNearData" )
    public void getTemplateNearData(HttpServletRequest request, HttpServletResponse response ,
                            @RequestParam(required = false) Double longitude,@RequestParam(required = false) Double latitude) {
    }





}
