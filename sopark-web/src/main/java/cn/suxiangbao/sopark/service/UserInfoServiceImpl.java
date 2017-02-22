package cn.suxiangbao.sopark.service;

import cn.suxiangbao.sopark.dao.UserInfoMongoDao;
import cn.suxiangbao.sopark.entity.CarPort;
import cn.suxiangbao.sopark.entity.UserInfo;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static cn.suxiangbao.sopark.http.BaseServletUtil.*;

/**
 * Created by sxb on 2017/2/22.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMongoDao userInfoMongoDao;

    @Override
    public void create(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo) {
        int retcode = SUCCESS;
        String msg = null;
        try {
            WriteResult result = userInfoMongoDao.insert(userInfo);
            if (result.getN() != 1){
                retcode = FAILED;
                msg = "init userInfo Error";
            }
        } catch (Exception e) {
            retcode = FAILED;
            msg = e.getMessage();
        }finally {
            sendResponse(request,response,genMsgObj(retcode,msg,userInfo));
        }
    }


    @Override
    public void update(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo) {
        int retcode = SUCCESS;
        String msg = null;
        try {
            WriteResult result = userInfoMongoDao.insertOrUpdate(userInfo);
            if (result.getN() == 0){
                msg = "user does not exist!";
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
    public void findByUid(HttpServletRequest request, HttpServletResponse response, Long uid) {
        int retcode = SUCCESS;
        String msg = null;
        UserInfo userInfo = null;
        try {
            userInfo = userInfoMongoDao.getById(uid);

        }catch (Exception e){
            msg = e.getMessage();
            retcode = FAILED;
        }finally {
            sendResponse(request,response,genMsgObj(retcode,msg,userInfo));
        }
    }

    @Override
    public void findByUsername(HttpServletRequest request, HttpServletResponse response, String username) {
        int retcode = SUCCESS;
        String msg = null;
        UserInfo userInfo = null;
        try {
            userInfo = userInfoMongoDao.findByUsername(username);
            if (userInfo == null){
                retcode = USER_NOT_EXIST;
                msg = "用户不存在";
            }
        } catch (Exception e) {
            retcode = FAILED;
            msg = e.getMessage();
        }finally {
            sendResponse(request,response,genMsgObj(retcode,msg,userInfo));
        }
    }

    @Override
    public void findByOthers(HttpServletRequest request, HttpServletResponse response, String others) {

    }
}
