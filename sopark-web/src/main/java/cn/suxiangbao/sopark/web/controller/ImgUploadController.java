package cn.suxiangbao.sopark.web.controller;

import cn.suxiangbao.sopark.Constants;
import cn.suxiangbao.sopark.entity.User;
import cn.suxiangbao.sopark.web.bind.annotation.CurrentUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import static cn.suxiangbao.sopark.http.BaseServletUtil.*;

/**
 * Created by sxb on 2017/2/26.
 */
@Controller
@RequestMapping("img")
public class ImgUploadController {
    @RequestMapping(value = "/upload")
    public void uploadHeader(@RequestParam(value="img",required = false) CommonsMultipartFile imgFile, HttpServletRequest request, @CurrentUser User user,
                             HttpServletResponse response) {
//
            if (user == null){
                sendResponse(request,response,genMsgObj(FAILED,"参数错误"));
                return;
            }
            if (imgFile== null  || imgFile.getBytes() == null || imgFile.getSize() == 0) {
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
            String tmpname = user.getId()+new Date().getTime()+"."+fileType;
            File file = new File(Constants.IMG_UPLOAD_PATH+tmpname);
            file.deleteOnExit();
            int retcode = SUCCESS;
            String msg= null;
            try {
                imgFile.transferTo(file);
            } catch (IOException e) {
                msg = e.getMessage();
                retcode = FAILED;
            }
            sendResponse(request,response,genMsgObj(retcode,msg,Constants.IMG_REMOTE_PATH+tmpname));

    }
}
