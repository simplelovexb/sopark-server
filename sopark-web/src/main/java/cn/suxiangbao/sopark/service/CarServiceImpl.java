package cn.suxiangbao.sopark.service;

import cn.suxiangbao.sopark.dao.CarMongoDao;
import cn.suxiangbao.sopark.entity.Car;
import cn.suxiangbao.sopark.entity.CarPort;
import cn.suxiangbao.sopark.http.BaseServletUtil;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static cn.suxiangbao.sopark.http.BaseServletUtil.*;

/**
 * Created by sxb on 2017/2/21.
 */
@Repository
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMongoDao carMongoDao;

    @Override
    public void add(HttpServletRequest request, HttpServletResponse response, Long uid, Car car) {
        Object resId =null;
        int retcode = SUCCESS;
        String msg = null;
        try {
            Date now = new Date();
            car.setUid(uid);
            car.setCid(carMongoDao.getNextId());
            car.setCreateDate(now);
            car.setUpdateDate(now);
            WriteResult result = carMongoDao.insert(car);
            resId = result.getUpsertedId();

        }catch (Exception e){
            msg = e.getMessage();
            retcode = FAILED;
        }finally {
            if (retcode == SUCCESS){
                sendResponse(request,response,genMsgObj(retcode,"",resId));
            }else {
                sendResponse(request,response,genMsgObj(retcode,msg));
            }
        }
    }

    @Override
    public void delete(HttpServletRequest request, HttpServletResponse response, Long uid, Car car) {
        int retcode = SUCCESS;
        String msg = null;
        try {
            WriteResult result = carMongoDao.removeById(car.getCid(),uid);
            if (result.getN() == 0){
                msg = "car not exit";
                retcode = FAILED;
            }
        }catch (Exception e){
            msg = e.getMessage();
            retcode = FAILED;
        }finally {
            sendResponse(request,response,genMsgObj(retcode,msg));
        }
    }

    @Override
    public void update(HttpServletRequest request, HttpServletResponse response, Long uid, Car car) {
        int retcode = SUCCESS;
        String msg = null;
        try {
            car.setUpdateDate(new Date());
            WriteResult result = carMongoDao.insertOrUpdate(car);
            if (result.getN() == 0){
                msg = "car not exit";
                retcode = FAILED;
            }
        }catch (Exception e){
            msg = e.getMessage();
            retcode = FAILED;
        }finally {
            sendResponse(request,response,genMsgObj(retcode,msg));
        }
    }

    @Override
    public void findOne(HttpServletRequest request, HttpServletResponse response, Long uid, Long cid) {
        int retcode = SUCCESS;
        String msg = null;
        Car car = null;
        try {
            car = carMongoDao.getById(cid,uid);

        }catch (Exception e){
            msg = e.getMessage();
            retcode = FAILED;
        }finally {
            sendResponse(request,response,genMsgObj(retcode,msg,car));
        }
    }

    @Override
    public void findByPage(HttpServletRequest request, HttpServletResponse response, Long uid, Pageable page) {
        int retcode = SUCCESS;
        String msg = null;
        List<Car> cars = null;
        try {
            cars = carMongoDao.findCarPortByUid(uid,page);
        }catch (Exception e){
            msg = e.getMessage();
            retcode = FAILED;
        }finally {
            BaseServletUtil.RetMsgObj ret = genMsgObj(retcode,msg,cars);
            sendResponse(request,response,ret);
        }
    }
}
