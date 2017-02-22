package cn.suxiangbao.sopark.dao;

import cn.suxiangbao.sopark.Constants;
import cn.suxiangbao.sopark.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by sxb on 2017/2/21.
 */
@Repository
public class CarMongoDao extends BaseMongoDao<Car> {
    @Autowired
    private MongoTemplate template;
    public CarMongoDao() {
        super(Constants.Collection.COLLECTION_CAR, Car.Field.FIELD_CID, Car.class);
    }

    @Override
    protected MongoTemplate getTemplate() {
        return this.template;
    }
}
