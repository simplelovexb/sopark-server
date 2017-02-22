package cn.suxiangbao.sopark.service;

import cn.suxiangbao.sopark.entity.Car;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sxb on 2017/2/21.
 */
@Repository
public class CarServiceImpl implements CarService {
    @Override
    public void add(HttpServletRequest request, HttpServletResponse response, Long uid, Car car) {

    }

    @Override
    public void delete(HttpServletRequest request, HttpServletResponse response, Long uid, Car car) {

    }

    @Override
    public void update(HttpServletRequest request, HttpServletResponse response, Long uid, Car car) {

    }

    @Override
    public void findOne(HttpServletRequest request, HttpServletResponse response, Long uid, Long pid) {

    }

    @Override
    public void findByPage(HttpServletRequest request, HttpServletResponse response, Long uid, Pageable page) {

    }
}
