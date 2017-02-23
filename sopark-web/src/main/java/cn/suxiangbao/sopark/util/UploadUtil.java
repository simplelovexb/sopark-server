package cn.suxiangbao.sopark.util;

import cn.suxiangbao.sopark.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import static cn.suxiangbao.sopark.http.BaseServletUtil.*;

public class UploadUtil {
	
	public static void upload(HttpServletRequest request, HttpServletResponse response,CommonsMultipartFile imgFile, String localName,String remoteName) throws IllegalStateException, IOException{
		//创建一个通用的多部分解析器  

        String tmpFileName = imgFile.getFileItem().getName();
        String fileType = StringUtils.substringAfterLast(tmpFileName, ".");
        if (!Constants.IMG_TYPE.contains(fileType)){
            sendResponse(request,response,genMsgObj(FAILED,"文件类型错误，必须为“png,jpg,jpeg”的图片"));
            return;
        }
        File file = new File(localName);
        file.deleteOnExit();
        int retcode = SUCCESS;
        String msg= null;
        try {
            imgFile.transferTo(file);
        } catch (IOException e) {
            msg = e.getMessage();
            remoteName = null;
            retcode = FAILED;
        }
        sendResponse(request,response,genMsgObj(retcode,msg,remoteName));
	}
}
