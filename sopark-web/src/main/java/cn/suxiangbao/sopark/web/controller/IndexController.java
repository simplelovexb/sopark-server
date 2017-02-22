package cn.suxiangbao.sopark.web.controller;

import cn.suxiangbao.sopark.Constants;
import cn.suxiangbao.sopark.entity.User;
import cn.suxiangbao.sopark.service.GeoService;
import cn.suxiangbao.sopark.service.UserService;
import cn.suxiangbao.sopark.web.bind.annotation.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
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
    @Autowired
    private GeoService geoService;

    @RequestMapping(value = "/getNearData")
    public void getNearData(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(required = false) Double longitude, @RequestParam(required = false) Double latitude,
                            @RequestParam(required = false,defaultValue = Constants.DEFAULT_DISTANCE) Double dis,
                            @PageableDefault(value = 10,page = 0)Pageable page) {
        Double[] coordinate = new Double[2];
        if (longitude==null || latitude ==null || longitude<-180 || longitude>180 || latitude>90 || latitude<-90){
            coordinate = Constants.DEFAULT_COORDINATE;
        }else{
            coordinate[0]=longitude;
            coordinate[1]=latitude;
        }
        if (dis<0) {
            dis = Double.parseDouble(Constants.DEFAULT_DISTANCE);
        }
        geoService.getNearCarPort(request,response,coordinate,dis,page);
    }



}
