import cn.suxiangbao.sopark.dao.GeoMongoDao;
import cn.suxiangbao.sopark.dao.GeoMongoDaoImpl;
import cn.suxiangbao.sopark.entity.CarPort;
import cn.suxiangbao.sopark.mongo.MongoUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * Created by sxb on 2017/2/21.
 */
public class GeoTest {

    public static void main(String[] args){
        DBObject object = new BasicDBObject(CarPort.Field.FIELD_COORDINATE,
                new BasicDBObject(MongoUtil.CMD_GEO_WITHIN,
                        new BasicDBObject(MongoUtil.GEO_CENTER_SPHERE,new Object[]{new Double[]{1.0,2.0},2})));
        object.put(CarPort.Field.FIELD_STATUS,CarPort.StatusEnum.CouldUse.getType());
        System.out.println(object);
    }
}
