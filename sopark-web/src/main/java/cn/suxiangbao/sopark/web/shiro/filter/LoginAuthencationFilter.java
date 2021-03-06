package cn.suxiangbao.sopark.web.shiro.filter;

import cn.suxiangbao.sopark.dao.UserInfoMongoDao;
import cn.suxiangbao.sopark.entity.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.AsyncContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static cn.suxiangbao.sopark.http.BaseServletUtil.*;

/**
 * Created by sxb on 2017/2/16.
 */
public class LoginAuthencationFilter extends FormAuthenticationFilter{
//    @Override
    @Autowired
    private UserInfoMongoDao userInfoMongoDao;

    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        if (getSuccessUrl().equals(getLoginUrl())){
            issueSuccessRedirect(request, response);
            return false;
        }
//        ((HttpServletResponse)response).setHeader();
        UserInfo userInfo = userInfoMongoDao.findByUsername((String) token.getPrincipal());
        sendResponse(request,response,genMsgObj(SUCCESS,"登录成功",userInfo));

        //we handled the success redirect directly, prevent the chain from continuing:

        return false;
    }

}
