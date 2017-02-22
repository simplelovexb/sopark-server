package cn.suxiangbao.sopark.service;

import cn.suxiangbao.sopark.entity.CarPort;
import cn.suxiangbao.sopark.entity.User;
import cn.suxiangbao.sopark.web.bind.annotation.CurrentUser;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cn.suxiangbao.sopark.http.BaseServletUtil.FAILED;
import static cn.suxiangbao.sopark.http.BaseServletUtil.genMsgObj;
import static cn.suxiangbao.sopark.http.BaseServletUtil.sendResponse;

/**
 * Created by sxb on 2017/2/21.
 */
public interface CarPortService {
    void add(HttpServletRequest request, HttpServletResponse response,Long uid, CarPort carPort);

    void delete(HttpServletRequest request, HttpServletResponse response,Long uid, CarPort carPort);

    void update(HttpServletRequest request,HttpServletResponse response,Long uid,CarPort carPort);

    void findOne(HttpServletRequest request,HttpServletResponse response,Long uid,Long pid);

    void findByPage(HttpServletRequest request, HttpServletResponse response,Long uid, Pageable page);
}
