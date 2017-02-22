package cn.suxiangbao.sopark.web.controller;

import cn.suxiangbao.sopark.entity.Car;
import cn.suxiangbao.sopark.entity.User;
import cn.suxiangbao.sopark.service.CarService;
import cn.suxiangbao.sopark.web.bind.annotation.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cn.suxiangbao.sopark.http.BaseServletUtil.*;

/**
 * Created by sxb on 2017/2/21.
 */
@Controller
@RequestMapping("car")
public class CarController  {

    @Autowired
    private CarService carService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public void add(HttpServletRequest request, HttpServletResponse response, @CurrentUser User user, Car car){
        if (user == null || car ==null){
            sendResponse(request,response,genMsgObj(FAILED,"params error"));
            return;
        }

        carService.add(request,response,user.getId(),car);
    }

    @RequestMapping(value = "update" ,method = RequestMethod.POST)
    public void update(HttpServletRequest request, HttpServletResponse response, @CurrentUser User user, Car car){
        if (user == null || car ==null){
            sendResponse(request,response,genMsgObj(FAILED,"params error"));
            return;
        }

        carService.update(request,response,user.getId(),car);
    }

    @RequestMapping(value = "delete" )
    public void delete(HttpServletRequest request, HttpServletResponse response, @CurrentUser User user, Car car){
        if (user == null || car ==null){
            sendResponse(request,response,genMsgObj(FAILED,"params error"));
            return;
        }

        carService.delete(request,response,user.getId(),car);
    }

    @RequestMapping(value = "findById" )
    public void findOne(HttpServletRequest request, HttpServletResponse response, @CurrentUser User user, Long pid){
        if (user == null || pid ==null){
            sendResponse(request,response,genMsgObj(FAILED,"params error"));
            return;
        }

        carService.findOne(request,response,user.getId(),pid);
    }

    @RequestMapping(value = "findByUid" )
    public void findByUid(HttpServletRequest request, HttpServletResponse response, @CurrentUser User user, @PageableDefault(value = 10,page = 0)Pageable page){
        if (user == null){
            sendResponse(request,response,genMsgObj(FAILED,"params error"));
            return;
        }

        carService.findByPage(request,response,user.getId(),page);
    }

}
