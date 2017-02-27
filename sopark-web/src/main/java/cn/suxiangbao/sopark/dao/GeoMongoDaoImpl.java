package cn.suxiangbao.sopark.dao;

import cn.suxiangbao.sopark.Constants;
import cn.suxiangbao.sopark.entity.CarPort;
import cn.suxiangbao.sopark.http.BaseServletUtil;
import cn.suxiangbao.sopark.mongo.MongoUtil;
import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sxb on 2017/2/21.
 */
@Repository
public class GeoMongoDaoImpl implements GeoMongoDao {

    @Autowired
    private MongoTemplate template;
    @Override
    public void getNearCarPort(HttpServletRequest request , HttpServletResponse response,Double[] loc, Double dis , Pageable page) {
        int rescode = BaseServletUtil.SUCCESS;
        String msg = null;
        List<CarPort> carPorts = new ArrayList<>();

        try{
            DBObject object = new BasicDBObject(CarPort.Field.FIELD_COORDINATE,
                    new BasicDBObject(MongoUtil.CMD_GEO_WITHIN,
                            new BasicDBObject(MongoUtil.GEO_CENTER_SPHERE,new Object[]{loc,dis})));
            object.put(CarPort.Field.FIELD_STATUS,CarPort.StatusEnum.CouldUse.getType());
            DBCursor cursor = template.getCollection(Constants.Collection.COLLECTION_CARPORT).find(object);
            cursor.skip(page.getOffset()).limit(page.getPageSize());
            while (cursor.hasNext()){
                DBObject obj = cursor.next();
                CarPort carPort = MongoUtil.db2JavaObj(template,CarPort.class,obj,CarPort.Field.FIELD_ID);
                if (carPort!=null){
                    carPorts.add(carPort);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            msg = e.getMessage();
            rescode = BaseServletUtil.FAILED;
        }finally {
            if (rescode == BaseServletUtil.SUCCESS){
                BaseServletUtil.sendResponse(request,response,BaseServletUtil.genMsgObj(rescode,msg,carPorts));
            }else {
                BaseServletUtil.sendResponse(request,response,BaseServletUtil.genMsgObj(rescode,msg));
            }
        }
    }
}
