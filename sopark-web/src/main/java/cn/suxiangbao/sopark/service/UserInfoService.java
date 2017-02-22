package cn.suxiangbao.sopark.service;

import cn.suxiangbao.sopark.entity.User;
import cn.suxiangbao.sopark.entity.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sxb on 2017/2/22.
 */
public interface UserInfoService {
    void create(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo);
    void update(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo);
    void findByUid(HttpServletRequest request, HttpServletResponse response, Long uid);
    void findByUsername(HttpServletRequest request, HttpServletResponse response, String username);
    void findByOthers(HttpServletRequest request, HttpServletResponse response, String others);
}
