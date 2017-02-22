package cn.suxiangbao.sopark.dao;

import cn.suxiangbao.sopark.Constants;
import cn.suxiangbao.sopark.entity.UserInfo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by sxb on 2017/2/22.
 *
 */
@Repository
public class UserInfoMongoDao extends BaseMongoDao<UserInfo> {

    @Autowired
    private MongoTemplate template;

    public UserInfoMongoDao() {
        super(Constants.Collection.COLLECTION_USER_INFO, UserInfo.Field.FIELD_UID, UserInfo.class);
    }

    @Override
    protected MongoTemplate getTemplate() {
        return this.template;
    }

    public UserInfo findByUsername(String username) throws Exception {
        DBObject object = new BasicDBObject(UserInfo.Field.FIELD_USERNAME,username);
        return getInstanceOfEntityClass(object);
    }


}
