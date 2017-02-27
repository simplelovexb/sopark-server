package cn.suxiangbao.sopark.service;

import cn.suxiangbao.sopark.entity.Car;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sxb on 2017/2/21.
 */
public interface CarService {
    void add(HttpServletRequest request, HttpServletResponse response, Long uid, Car car);

    void delete(HttpServletRequest request, HttpServletResponse response,Long uid, Car car);

    void update(HttpServletRequest request,HttpServletResponse response,Long uid,Car car);

    void findOne(HttpServletRequest request,HttpServletResponse response,Long uid,Long cid);

    void findByPage(HttpServletRequest request, HttpServletResponse response,Long uid, Pageable page);
}
