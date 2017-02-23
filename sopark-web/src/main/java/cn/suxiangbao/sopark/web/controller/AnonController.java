package cn.suxiangbao.sopark.web.controller;

import cn.suxiangbao.sopark.Constants;
import cn.suxiangbao.sopark.dao.UserDao;
import cn.suxiangbao.sopark.entity.User;
import cn.suxiangbao.sopark.http.BaseServletUtil;
import cn.suxiangbao.sopark.service.GeoService;
import cn.suxiangbao.sopark.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    private UserService userService;

    @Autowired
    private GeoService geoService;
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
        sendResponse(request,response,genMsgObj(SUCCESS));
    }

    @RequestMapping(value = "/isLogin",method = RequestMethod.GET)
    public void isLogin(HttpServletRequest request, HttpServletResponse response){
        Boolean isLogin = SecurityUtils.getSubject().isAuthenticated();
        if (isLogin){
            sendResponse(request,response,genMsgObj(SUCCESS));
        }else{
            sendResponse(request,response,genMsgObj(FAILED));
        }

    }
    @RequestMapping(value = "/getTemplateNearData",method = RequestMethod.GET)
    public void getTemplateNearData(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(required = false) Double longitude,@RequestParam(required = false) Double latitude,
                                    @RequestParam(required = false) Double dis, @PageableDefault(value = 10, page = 0) Pageable page){

        Double[] coordinate = new Double[2];
        if (longitude==null || latitude ==null || longitude<-180 || longitude>180 || latitude>90 || latitude<-90){
            coordinate = Constants.DEFAULT_COORDINATE;
        }else{
            coordinate[0]=longitude;
            coordinate[1]=latitude;
        }
        geoService.getNearCarPort(request,response,coordinate,dis,page);


    }
}
