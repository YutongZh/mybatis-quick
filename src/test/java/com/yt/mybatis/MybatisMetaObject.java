package com.yt.mybatis;

import com.yt.mybatis.entity.TUser;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//模拟ORM框架查询数据过程
public class MybatisMetaObject {

    @Test
    public void test01(){

        //反射工具类初始化
        ObjectFactory objectFactory = new DefaultObjectFactory();
        TUser tUser = objectFactory.create(TUser.class);
        ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();
        ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
        MetaObject metaObject = MetaObject.forObject(tUser, objectFactory, objectWrapperFactory, reflectorFactory);
        /*
         * 模拟数据库行数据库转化成对象
         */
        //模拟从数据库读取数据
        Map<String, Object> dbResult = new HashMap<>();
        dbResult.put("id", 1);
        dbResult.put("user_name", "jay");
        dbResult.put("real_name", "杰伦");
        dbResult.put("position_id", 2);

        //模拟映射关系
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("id", "id");
        resultMap.put("userName", "user_name");
        resultMap.put("realName", "real_name");
        resultMap.put("positionId","position_id");

        //将行数据转化为pojo
        ObjectWrapper objectWrapper = metaObject.getObjectWrapper();

        //属性赋值
        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
            String propName = entry.getKey();
            Object propValue = dbResult.get(entry.getValue());
            PropertyTokenizer propertyTokenizer = new PropertyTokenizer(propName);
            objectWrapper.set(propertyTokenizer, propValue);
        }
        System.out.println(metaObject.getOriginalObject());
    }
}
