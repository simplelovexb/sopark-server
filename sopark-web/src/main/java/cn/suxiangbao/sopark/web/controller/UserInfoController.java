package cn.suxiangbao.sopark.web.controller;

import cn.suxiangbao.sopark.Constants;
import cn.suxiangbao.sopark.entity.User;
import cn.suxiangbao.sopark.entity.UserInfo;
import cn.suxiangbao.sopark.service.UserInfoService;
import cn.suxiangbao.sopark.web.bind.annotation.CurrentUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import static cn.suxiangbao.sopark.http.BaseServletUtil.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sxb on 2017/2/22.
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void updateUserInfo(HttpServletRequest request, HttpServletResponse response, @CurrentUser User user, UserInfo userInfo){
        if (user==null || userInfo == null){
            sendResponse(request,response,genMsgObj(USERNAME_EXISTED,"用户不存在"));
            return;
        }
        userInfoService.update(request,response,userInfo);
    }

    /**
     * 只能用于找用户自己的信息
     * @param request
     * @param response
     * @param user
     */
    @RequestMapping(value = "findSelfInfo")
    public void findUserInfoById(HttpServletRequest request,HttpServletResponse response,@CurrentUser User user){
        if (user == null){
            sendResponse(request,response,genMsgObj(USERNAME_EXISTED,"用户不存在"));
            return;
        }
        userInfoService.findByUid(request,response,user.getId());

    }

    @RequestMapping(value = "findByUsername")
    public void findUserInfoByUsername(HttpServletRequest request,HttpServletResponse response,@CurrentUser User user,String username){
        if (user == null){
            sendResponse(request,response,genMsgObj(FAILED,"参数错误"));
            return;
        }
        userInfoService.findByUsername(request,response,username);
    }

    @RequestMapping(value = "/uploadIcon")
    public void uploadHeader(@RequestParam("file") CommonsMultipartFile imgFile, HttpServletRequest request,@CurrentUser User user,
                             HttpServletResponse response) {

        if (user == null){
            sendResponse(request,response,genMsgObj(FAILED,"参数错误"));
            return;
        }
        if (imgFile.getBytes() == null || imgFile.getSize() == 0) {
            sendResponse(request, response,
                    genMsgObj(FAILED, "Req Param Not Right: imgFile.getBytes() == null || imgFile.getSize() == 0"));
            return;
        }
        String fileName = imgFile.getFileItem().getName();
        String fileType = StringUtils.substringAfterLast(fileName, ".");
        if (!Constants.IMG_TYPE.contains(fileType)){
            sendResponse(request,response,genMsgObj(FAILED,"文件类型错误，必须为“png,jpg,jpeg”的图片"));
            return;
        }
        File file = new File(Constants.IMG_UPLOAD_PATH+user.getId()+"_icon."+fileType);
        file.deleteOnExit();
        int retcode = SUCCESS;
        String msg= null;
        try {
            imgFile.transferTo(file);
        } catch (IOException e) {
            msg = e.getMessage();
            retcode = FAILED;
        }
        sendResponse(request,response,genMsgObj(retcode,msg,Constants.IMG_REMOTE_PATH+user.getId()+"_icon."+fileType));

    }

}
