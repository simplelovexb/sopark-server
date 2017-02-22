package cn.suxiangbao.sopark.service;

import cn.suxiangbao.sopark.dao.CarPortMongoDao;
import cn.suxiangbao.sopark.entity.CarPort;
import static cn.suxiangbao.sopark.http.BaseServletUtil.*;

import cn.suxiangbao.sopark.mongo.MongoUtil;
import com.mongodb.WriteResult;
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
public class CarPortServiceImpl implements CarPortService {

    @Autowired
    private CarPortMongoDao carPortMongoDao;
    @Override
    public void add(HttpServletRequest request, HttpServletResponse response,Long uid, CarPort carPort) {
        Object resId =null;
        int retcode = SUCCESS;
        String msg = null;
        try {
            carPort.setOwner(uid);
            if (carPort.getStatus() ==null){
                carPort.setStatus(CarPort.StatusEnum.CouldUse.getType());
                carPort.setVerify(CarPort.VerifyEnum.UnVerify.getType());
            }
            WriteResult result = carPortMongoDao.insert(carPort);
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
    public void delete(HttpServletRequest request, HttpServletResponse response,Long uid, CarPort carPort) {
        int retcode = SUCCESS;
        String msg = null;
        try {
            WriteResult result = carPortMongoDao.removeById(carPort.getPid(),uid);
            if (result.getN() == 0){
                msg = "carport not exit";
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
    public void update(HttpServletRequest request, HttpServletResponse response,Long uid, CarPort carPort) {
        int retcode = SUCCESS;
        String msg = null;
        try {
            WriteResult result = carPortMongoDao.insertOrUpdate(carPort);
            if (result.getN() == 0){
                msg = "carport not exit";
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
    public void findOne(HttpServletRequest request, HttpServletResponse response,Long uid, Long pid) {
        int retcode = SUCCESS;
        String msg = null;
        CarPort carPort = null;
        try {
            carPort = carPortMongoDao.getById(pid,uid);

        }catch (Exception e){
            msg = e.getMessage();
            retcode = FAILED;
        }finally {
            sendResponse(request,response,genMsgObj(retcode,msg,carPort));
        }
    }

    @Override
    public void findByPage(HttpServletRequest request, HttpServletResponse response,Long uid , Pageable page) {
        int retcode = SUCCESS;
        String msg = null;
        List<CarPort> carPorts = null;
        try {
            carPorts = carPortMongoDao.findCarPortByUid(uid,page);
        }catch (Exception e){
            msg = e.getMessage();
            retcode = FAILED;
        }finally {
            sendResponse(request,response,genMsgObj(retcode,msg,carPorts));
        }
    }
}
