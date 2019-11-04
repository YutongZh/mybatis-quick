package com.yt.mybatis;

import com.yt.mybatis.entity.TUser;
import com.yt.mybatis.mapper.TUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisQuickStart {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        String source = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(source);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        inputStream.close();
    }

    @Test
    public void quickStart(){
        //获取sqlsession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取对应的mapper
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        //执行语句并返回结果
        TUser user = mapper.getTUserByPrimaryKey(1);
        System.out.println(user);
    }

}
