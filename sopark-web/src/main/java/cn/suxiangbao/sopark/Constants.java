package cn.suxiangbao.sopark;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-15
 * <p>Version: 1.0
 */
public class Constants {
    public static final String CURRENT_USER = "user";
    public static final Integer DEFAULT_QUERY_LIMIT = 10;
    public static final String DEFAULT_DISTANCE = "1.0"; //默认距离
    public static final String IMG_UPLOAD_PATH="";
    public static final String IMG_REMOTE_PATH="";
    public static final List<String> IMG_TYPE = Arrays.asList("jpg","png","jpeg");
    public static class Collection{
        public static final String COLLECTION_CARPORT = "carport";
        public static final String COLLECTION_CAR= "car";
        public static final String COLLECTION_USER_INFO = "userInfo";
    }

    public static final Double[] DEFAULT_COORDINATE=new Double[]{113.331374,23.143602};//天河体育中心的位置

}
