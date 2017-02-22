package cn.suxiangbao.sopark.dao;

import cn.suxiangbao.sopark.mongo.MongoUtil;
import com.mongodb.*;
import org.apache.commons.collections.CollectionUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.*;

/**
 * mongodb操作模板类
 * <p/>
 * Created by hezhongyang@yy.com on 2016/8/8.
 */
public abstract class BaseMongoDao<T> {

    private static Logger logger = LoggerFactory.getLogger(BaseMongoDao.class);

    public static final int ASC = 1;
    public static final int DESC = -1;

    /**
     * 集合名字
     */
    protected final String COLLECTION_NAME;

    /**
     * mongodb集合主键
     */
    protected final String COLLECTION_PRIMARY_KEY = "_id";

    /**
     * 实体类主键对应于mongodb主键_id.
     */
    protected final String FIELD_PRIMARY_KEY;

    protected final Class<T> entityClazz;

    //必须继承实现
    public BaseMongoDao(String collectionName, String entityIdFieldName, Class<T> clazz) {
        COLLECTION_NAME = collectionName;
        FIELD_PRIMARY_KEY = entityIdFieldName;
        this.entityClazz = clazz;
    }

    //插入
    public WriteResult insert(T t) throws Exception {
        DBObject obj = MongoUtil.javaObj2Db(getTemplate(), t, FIELD_PRIMARY_KEY);
        return getCollection().insert(obj);
    }

    //插入或更新
    public WriteResult insertOrUpdate(T t) throws Exception {
        DBObject obj = MongoUtil.javaObj2Db(getTemplate(), t, FIELD_PRIMARY_KEY);
        return getCollection().save(obj);
    }

    //更新
    protected WriteResult update(final DBObject query, final DBObject update, final boolean upsert, final boolean multi) {
        return getCollection().update(query, update, upsert, multi);
    }

    //更新
    protected WriteResult setUpdate(final DBObject query, final DBObject updateContent, final boolean upsert, final boolean multi) {
        return getCollection().update(query, new BasicDBObject("$set", updateContent), upsert, multi);
    }

    //通过id获得实例
    public T getById(Object id) throws Exception {
        if (id instanceof String)
            return getInstanceOfEntityClass(query(new BasicDBObject("_id", new ObjectId((String) id))));
        if (id instanceof Date)
            return getInstanceOfEntityClass(query(new BasicDBObject("_id", new ObjectId((Date) id))));
        if (id instanceof byte[])
            return getInstanceOfEntityClass(query(new BasicDBObject("_id", new ObjectId((byte[]) id))));
        return getInstanceOfEntityClass(query(new BasicDBObject("_id", id)));
    }

    //获得实体类的一个实例
    protected T getInstanceOfEntityClass(DBCursor cursor) throws Exception {
        T t = null;
        while (cursor.hasNext()) {
            DBObject dbObject = cursor.next();
            return MongoUtil.db2JavaObj(getTemplate(), entityClazz, dbObject, FIELD_PRIMARY_KEY);
        }
        return t;
    }

    //获得实体类的一个实例
    protected T getInstanceOfEntityClass(DBObject dbObject) throws Exception {
        return getInstanceOfEntityClass(query(dbObject));
    }

    //获得聚合后的DBObject
    protected DBObject getAggregateDBObject(List<DBObject> aggregateConditions) throws Exception {
        AggregationOutput output = getCollection().aggregate(aggregateConditions);
        if (output != null || output.results() != null) {
            Iterator<DBObject> iterator = output.results().iterator();
            if (iterator.hasNext()) {
                return iterator.next();
            }
        }
        return null;
    }

    //获得聚合后的DBObjectList
    protected List<DBObject> getAggregateDBObjectList(List<DBObject> aggregateConditions) throws Exception {
        AggregationOutput output = getCollection().aggregate(aggregateConditions);
        List<DBObject> list = new ArrayList<>();
        if (output != null || output.results() != null) {
            Iterator<DBObject> iterator = output.results().iterator();
            if (iterator.hasNext()) {
                list.add(iterator.next());
            }
        }
        return list;
    }

    //获得实体类的list
    protected List<T> getAggregateList(List<DBObject> aggregateConditions) throws Exception {
        List<T> list = new ArrayList<T>();
        AggregationOutput output = getCollection().aggregate(aggregateConditions);
        if (output != null || output.results() != null) {
            Iterator<DBObject> iterator = output.results().iterator();
            if (iterator.hasNext()) {
                DBObject obj = iterator.next();
                T e = MongoUtil.db2JavaObj(getTemplate(), entityClazz, obj,FIELD_PRIMARY_KEY);
                list.add(e);
            }
        }
        if (CollectionUtils.isEmpty(list))
            return null;
        return list;
    }

    //获得实体类的list
    protected List<T> getListOfEntityClass(DBCursor cursor) throws Exception {
        List<T> list = new ArrayList<T>();
        if (cursor == null) return list;
        while (cursor.hasNext()) {
            DBObject dbObject = cursor.next();
            list.add(MongoUtil.db2JavaObj(getTemplate(), entityClazz, dbObject, FIELD_PRIMARY_KEY));
        }
        return list;
    }

    //查询
    protected DBCursor query(final DBObject query) {
        DBCursor cursor = null;
        cursor = getCollection().find(query);
        return cursor;
    }

    protected long count(final DBObject query) {
        return getCollection().count(query);
    }

    // 分页
    protected DBCursor page(DBCursor cursor, Pageable page) {
        if (cursor != null && page != null) {
            if (page.getOffset() > 0) {
                cursor.skip(page.getOffset());
            }
            if (page.getPageSize() > 0) {
                cursor.limit(page.getPageSize());
            }
        }
        return cursor;
    }

    // 聚合分页
    protected List<DBObject> page(List<DBObject> aggregateConditions, Pageable page) {
        if (page != null) {
            if (page.getOffset() > 0) {
                DBObject skip = new BasicDBObject("$skip", page.getOffset());
                aggregateConditions.add(skip);
            }
            if (page.getPageSize() > 0) {
                DBObject limit = new BasicDBObject("$limit", page.getPageSize());
                aggregateConditions.add(limit);
            }
        }
        return aggregateConditions;
    }

    // 聚合分组
    protected List<DBObject> group(List<DBObject> aggregateConditions, DBObject groupCondition) {
        if (aggregateConditions != null && groupCondition != null) {
            DBObject group = new BasicDBObject();
            group.put("$group", groupCondition);
            aggregateConditions.add(group);
        }
        return aggregateConditions;
    }

    // 聚合匹配
    protected List<DBObject> match(List<DBObject> aggregateConditions, DBObject matchCondition) {
        if (aggregateConditions != null && matchCondition != null) {
            DBObject group = new BasicDBObject();
            group.put("$match", matchCondition);
            aggregateConditions.add(group);
        }
        return aggregateConditions;
    }

    // 聚合排序
    protected List<DBObject> sort(List<DBObject> aggregateConditions, DBObject sortCondition) {
        if (aggregateConditions != null && sortCondition != null) {
            DBObject group = new BasicDBObject();
            group.put("$sort", sortCondition);
            aggregateConditions.add(group);
        }
        return aggregateConditions;
    }

    //排序
    protected DBCursor sort(DBCursor cursor, DBObject sortCondition) {
        if (cursor != null && sortCondition != null) {
            cursor.sort(sortCondition);
        }
        return cursor;
    }

    protected Set<ObjectId> strIdsToObjIds(List<String> strIds) {
        Set<ObjectId> objectIdSet = new HashSet<>();
        if (CollectionUtils.isEmpty(strIds)) return objectIdSet;
        for (String str : strIds) {
            try{
                objectIdSet.add(new ObjectId(str));
            }catch(Exception ex)//有些主播的主键不是ObjectId类型，会抛出异常
            {
                logger.error("strIdsToObjIds Exception:", ex);
            }
        }
        return objectIdSet;
    }

    //通过id删除//这是通用的，如果有特殊逻辑需要子类重写
    protected WriteResult removeById(Object id,DBObject obj) {
        DBObject query = new BasicDBObject();
        if (obj !=null){
            query = obj;
        }
        if (id instanceof String){
            query.put("_id", new ObjectId((String) id));
        }else if (id instanceof Date){
            query.put("_id", new ObjectId((Date) id));

        }else if (id instanceof byte[]){
            query.put("_id",new ObjectId((byte[]) id));
        }else{
            query.put("_id", id);
        }
        return getCollection().remove(query);
    }

    //删除所有
    public WriteResult removeAll() {
        return getCollection().remove(new BasicDBObject());
    }

    //删除部分
    public WriteResult removeSome(String keyName, Object value) {
        return getCollection().remove(new BasicDBObject(keyName, value));
    }

    //计算所有记录数
    public long countAll() {
        return getCollection().count();
    }

    //获取所有记录
    public List<T> getAllEntityList() throws Exception {
        return getListOfEntityClass(query(new BasicDBObject()));
    }

    protected abstract MongoTemplate getTemplate();


    protected DBCollection getCollection() {
        return getTemplate().getDb().getCollection(COLLECTION_NAME);
    }
}
