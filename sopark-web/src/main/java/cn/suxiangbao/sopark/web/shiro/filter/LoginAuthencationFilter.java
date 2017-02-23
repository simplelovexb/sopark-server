package cn.suxiangbao.sopark.web.shiro.filter;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import static cn.suxiangbao.sopark.http.BaseServletUtil.*;

/**
 * Created by sxb on 2017/2/16.
 */
public class LoginAuthencationFilter extends FormAuthenticationFilter{
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {

        if (getSuccessUrl().equals(getLoginUrl())){
            issueSuccessRedirect(request, response);
            return false;
        }
        sendResponse(request,response,genMsgObj(SUCCESS,"登录成功"));
        //we handled the success redirect directly, prevent the chain from continuing:
        return false;
    }

}
