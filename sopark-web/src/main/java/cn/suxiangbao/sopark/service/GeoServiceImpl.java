package cn.suxiangbao.sopark.service;

import cn.suxiangbao.sopark.dao.GeoMongoDao;
import cn.suxiangbao.sopark.entity.CarPort;
import cn.suxiangbao.sopark.http.BaseServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by sxb on 2017/2/21.
 */
@Service
public class GeoServiceImpl implements GeoService {

    @Autowired
    private GeoMongoDao geoMongoDao;

    @Override
    public void getNearCarPort(HttpServletRequest request , HttpServletResponse response, Double[] loc, Double dis, Pageable page) {
       geoMongoDao.getNearCarPort(request,response,loc,dis,page);

    }
}
