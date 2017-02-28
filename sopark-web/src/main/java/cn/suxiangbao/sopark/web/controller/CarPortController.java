package cn.suxiangbao.sopark.web.controller;

import cn.suxiangbao.sopark.entity.CarPort;
import cn.suxiangbao.sopark.entity.User;
import cn.suxiangbao.sopark.json.JsonUtil;
import cn.suxiangbao.sopark.service.CarPortService;
import cn.suxiangbao.sopark.web.bind.annotation.CurrentUser;
import com.google.common.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static cn.suxiangbao.sopark.http.BaseServletUtil.*;

/**
 * Created by sxb on 2017/2/21.
 */
@RequestMapping("carPort")
@Controller
public class CarPortController {

    @Autowired
    private CarPortService carPortService;
    @Autowired
    private JsonUtil jsonUtil;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public void add(HttpServletRequest request, HttpServletResponse response, @CurrentUser User user, CarPort carPort){
        if (user == null || carPort ==null){
            sendResponse(request,response,genMsgObj(FAILED,"params error"));
            return;
        }


        carPortService.add(request,response,user.getId(),carPort);
    }

    @RequestMapping(value = "update" ,method = RequestMethod.POST)
    public void update(HttpServletRequest request, HttpServletResponse response, @CurrentUser User user, CarPort carPort){
        if (user == null || carPort ==null){
            sendResponse(request,response,genMsgObj(FAILED,"params error"));
            return;
        }

        carPortService.update(request,response,user.getId(),carPort);
    }

    @RequestMapping(value = "delete" )
    public void delete(HttpServletRequest request, HttpServletResponse response, @CurrentUser User user, CarPort carPort){
        if (user == null || carPort ==null){
            sendResponse(request,response,genMsgObj(FAILED,"params error"));
            return;
        }

        carPortService.delete(request,response,user.getId(),carPort);
    }

    @RequestMapping(value = "findById" )
    public void findOne(HttpServletRequest request, HttpServletResponse response, @CurrentUser User user, Long pid){
        if (user == null || pid ==null){
            sendResponse(request,response,genMsgObj(FAILED,"params error"));
            return;
        }

        carPortService.findOne(request,response,user.getId(),pid);
    }
    @RequestMapping(value = "findByUid" )
    public void findByUid(HttpServletRequest request, HttpServletResponse response, @CurrentUser User user, @PageableDefault(value = 10,page = 0)Pageable page){
        if (user == null){
            sendResponse(request,response,genMsgObj(FAILED,"params error"));
            return;
        }

        carPortService.findByPage(request,response,user.getId(),page);
    }


}
