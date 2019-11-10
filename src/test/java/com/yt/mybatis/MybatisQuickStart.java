package com.yt.mybatis;

import com.yt.mybatis.entity.TOrder;
import com.yt.mybatis.entity.TUser;
import com.yt.mybatis.mapper.TOrderMapper;
import com.yt.mybatis.mapper.TUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void test1(){
        //获取sqlsession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取对应的mapper
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        //执行语句并返回结果
        TUser user = mapper.getTUser1("li", 2);
        System.out.println(user);
    }

    @Test
    public void test2(){
        //获取sqlsession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取对应的mapper
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        TUser tUser = new TUser();
        tUser.setUserName("li");
        tUser.setId(2);
        tUser.setSex(2);
        //执行语句并返回结果
        TUser user = mapper.getTUser2(tUser);
        System.out.println(user);
    }

    @Test
    public void test3(){
        //获取sqlsession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取对应的mapper
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        TUser tUser = new TUser();
        tUser.setUserName("li");
        tUser.setId(2);
        tUser.setSex(2);
        //执行语句并返回结果
        TUser user = mapper.getTUser3(tUser);
        System.out.println(user);
    }

    //插入数据库 返回主键 方式一
    @Test
    public void  test4(){
        //获取sqlsession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取对应的mapper
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        TUser tUser = new TUser();
        tUser.setUserName("apache");
        tUser.setSex(2);
        tUser.setPositionId(4);
        tUser.setMobile("323223");
        tUser.setRealName("Bob");
        tUser.setNote("bobnote");
        tUser.setEmail("111@qq.com");
        //执行语句并返回结果
        Integer count = mapper.insertTUser4(tUser);
        sqlSession.commit();
        System.out.println(tUser.getId());
    }

    //插入数据库 返回主键 方式二
    @Test
    public void  test5(){
        //获取sqlsession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取对应的mapper
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        TUser tUser = new TUser();
        tUser.setUserName("apache");
        tUser.setSex(1);
        tUser.setPositionId(4);
        tUser.setMobile("2323232");
        tUser.setRealName("maven");
        tUser.setNote("mavenNote");
        tUser.setEmail("11111@qq.com");
        //执行语句并返回结果
        Integer count = mapper.insertTUser5(tUser);
        sqlSession.commit();
        System.out.println(tUser.getId());
    }

    //where if 语句用法
    @Test
    public void queryBywhere(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        TUser tUser = mapper.queryByWhere("li", 2, 2);
        System.out.println(tUser);
    }

    //update set语句
    @Test
    public void updateBySet(){
        //默认自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
         mapper.updateBySet(11, "zyt", "4534@qq.com",null);
    }

    @Test
    public void insertByTrim(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        TUser tUser = new TUser();
        tUser.setEmail("abc@qq.com");
        tUser.setNote("abcnote");
        tUser.setMobile("2322");
        tUser.setPositionId(2);
        tUser.setSex(2);
        tUser.setRealName("sds");
        tUser.setUserName("huahua");
        Integer integer = mapper.insertByTrim(tUser);
        System.out.println("成功=====" + integer);


        sqlSession.commit();
    }

    @Test
    public void selectByKey(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TOrderMapper mapper = sqlSession.getMapper(TOrderMapper.class);
        TOrder tOrder = mapper.selectByKey(1);
        System.out.println(tOrder);
    }

    //foreach array
    @Test
    public void selectByForEach(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        List<TUser> tUsers = mapper.selectByForEach(new String[]{"huahua", "li"});
        System.out.println(tUsers);
    }

    //foreach list
    @Test
    public void selectByForEachList(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        List list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        List<TUser> tUsers = mapper.selectByForEachList(list);
        System.out.println(tUsers);
    }

    // choose when  需求：优先通过性别 和 真实名称去查，字段有一个不存在则再通过姓名查
    @Test
    public void selectByChoose(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TUserMapper tUserMapper = sqlSession.getMapper(TUserMapper.class);
        TUser tUser = new TUser();
        tUser.setSex(2);


        tUser.setUserName("zyt");
        TUser tUser1 = tUserMapper.selectByChoose(tUser);

        System.out.println(tUser1);


    }

    //动态批量插入1 动态拼装sql
    @Test
    public void batchInsert(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        List list = new ArrayList<TUser>();
        TUser tUser = new TUser();
        tUser.setId(0);
        tUser.setUserName("zed");
        tUser.setRealName("劫");
        tUser.setSex(1);
        tUser.setEmail("23223@qq.com");
        tUser.setPositionId(5);
        tUser.setMobile("12222");
        tUser.setNote("4523Note");
        list.add(tUser);

        TUser tUser1 = new TUser();
        tUser1.setId(0);
        tUser1.setUserName("jax");
        tUser1.setRealName("武器大师");
        tUser1.setSex(1);
        tUser1.setEmail("223@qq.com");
        tUser1.setPositionId(13);
        tUser1.setMobile("232");
        tUser1.setNote("43Note");
        list.add(tUser1);


        Integer integer = mapper.batchInsert(list);
        System.out.println(integer);
    }

    //动态批量插入 batchEXTOR 执行器
    @Test
    public void batchInsertExcutor(){
        //手动指定执行器
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,true);
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        TUser tUser = new TUser();
        tUser.setEmail("abc@qq.com");
        tUser.setNote("abcnote");
        tUser.setMobile("2322");
        tUser.setPositionId(2);
        tUser.setSex(2);
        tUser.setRealName("金克丝");
        tUser.setUserName("jxs");
        mapper.insertByTrim(tUser);
        mapper.updateBySet(1079, "zyt", "4534@qq.com",null);
        sqlSession.commit();
    }

}
