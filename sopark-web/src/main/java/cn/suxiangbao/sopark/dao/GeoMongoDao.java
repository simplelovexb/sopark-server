package cn.suxiangbao.sopark.dao;

import cn.suxiangbao.sopark.entity.CarPort;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by sxb on 2017/2/21.
 */
public interface GeoMongoDao {
    void getNearCarPort(HttpServletRequest request , HttpServletResponse response,Double[] loc, Double dis, Pageable page);
}
