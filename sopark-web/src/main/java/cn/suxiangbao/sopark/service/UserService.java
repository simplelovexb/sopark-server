package cn.suxiangbao.sopark.service;

import cn.suxiangbao.sopark.entity.User;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface UserService {

    /**
     * 创建用户
     * @param user
     */
    public User createUser(HttpServletRequest request, HttpServletResponse response, User user);

    public User updateUser(HttpServletRequest request, HttpServletResponse response,User user);

    public void deleteUser(HttpServletRequest request, HttpServletResponse response,Long userId);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(HttpServletRequest request, HttpServletResponse response,Long userId, String newPassword);


    User findOne(HttpServletRequest request, HttpServletResponse response,Long userId);

    List<User> findAll(HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findByUsername(ServletRequest request, ServletResponse response, String username);
    public User findByUsername(String username);


}
