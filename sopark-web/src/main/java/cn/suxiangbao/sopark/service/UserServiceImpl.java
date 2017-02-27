package cn.suxiangbao.sopark.service;

import cn.suxiangbao.sopark.dao.UserDao;
import cn.suxiangbao.sopark.dao.UserInfoMongoDao;
import cn.suxiangbao.sopark.entity.User;
import cn.suxiangbao.sopark.entity.UserInfo;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordHelper passwordHelper;
    @Autowired
    private UserInfoMongoDao userInfoMongoDao;
    /**
     * 创建用户
     * @param user
     */
    public User createUser(HttpServletRequest request, HttpServletResponse response,User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        userDao.createUser(user);
        UserInfo userInfo = new UserInfo();
        userInfo.setAuthType(UserInfo.AuthType.NO_AUTH.getValue());
        userInfo.setUid(user.getId());
        userInfo.setUsername(user.getUsername());
        userInfo.setCreateDate(new Date());
        try {
            userInfoMongoDao.insert(userInfo);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User updateUser(HttpServletRequest request, HttpServletResponse response,User user) {
        return userDao.updateUser(user);
    }

    @Override
    public void deleteUser(HttpServletRequest request, HttpServletResponse response,Long userId) {
        userDao.deleteUser(userId);
    }

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(HttpServletRequest request, HttpServletResponse response,Long userId, String newPassword) {
        User user =userDao.findOne(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userDao.updateUser(user);
    }

    @Override
    public User findOne(HttpServletRequest request, HttpServletResponse response,Long userId) {
        return userDao.findOne(userId);
    }

    @Override
    public List<User> findAll(HttpServletRequest request, HttpServletResponse response) {
        return userDao.findAll();
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findByUsername(ServletRequest request, ServletResponse response, String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
