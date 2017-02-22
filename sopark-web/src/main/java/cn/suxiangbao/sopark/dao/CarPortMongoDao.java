package cn.suxiangbao.sopark.dao;

import cn.suxiangbao.sopark.Constants;
import cn.suxiangbao.sopark.entity.CarPort;
import cn.suxiangbao.sopark.mongo.MongoUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sxb on 2017/2/21.
 */
@Repository
public class CarPortMongoDao extends BaseMongoDao<CarPort> {


    @Autowired
    private MongoTemplate template;
    public CarPortMongoDao() {
        super(Constants.Collection.COLLECTION_CARPORT, CarPort.Field.FIELD_ID, CarPort.class);
    }
    @Override
    protected MongoTemplate getTemplate() {
        return this.template;
    }

    public List<CarPort> findCarPortByUid(Long uid, Pageable page) throws Exception {
        BasicDBObject dbObject = new BasicDBObject(CarPort.Field.FIELD_OWNER,uid);
        List<DBObject> aggregateConditions =  new ArrayList<DBObject>();
        match(aggregateConditions,new BasicDBObject(MongoUtil.OP_MATCH,dbObject));
        page(aggregateConditions,page);
        return getAggregateList(aggregateConditions);
    }

    public CarPort getById(Long pid,Long uid) throws Exception {
        DBObject object = new BasicDBObject(CarPort.Field.FIELD_OWNER,uid);
        object.put(MongoUtil.FIELD_OBJ_ID,pid);
        return super.getInstanceOfEntityClass(object);
    }

    public WriteResult removeById(Object id, Long uid) {
        DBObject object = new BasicDBObject(CarPort.Field.FIELD_OWNER,uid);
        return super.removeById(id, object);
    }
}
