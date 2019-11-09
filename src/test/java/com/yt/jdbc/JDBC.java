package com.yt.jdbc;

import com.yt.mybatis.entity.TUser;
import org.junit.Test;

import java.sql.*;

public class JDBC {
    static final String URL = "jdbc:mysql://localhost:3306/test";
    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String USE = "root";
    static final String PWD = "";


    @Test
    public void query(){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            //注册mysql驱动
            Class.forName(DRIVER);

            //获得一个链接
            connection = DriverManager.getConnection(URL, USE, PWD);

            //创建一个查询
            String sql = "select * from t_user where id= ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"1");
            resultSet = preparedStatement.executeQuery();

            //从resultSet获取数据
            while (resultSet.next()){
                System.out.println("=================");
//                TUser tUser = new TUser();
//                tUser.setId(resultSet.getInt("id"));
//                tUser.setUserName(resultSet.getString("user_name"));
//                tUser.setRealName(resultSet.getString("real_name"));
//                tUser.setMobile(resultSet.getString("mobile"));
//                tUser.setPositionId(resultSet.getInt("position_id"));

//                System.out.println(tUser);
            }

            //关闭流
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e){
        } catch (SQLException se){
        } catch (Exception e){
        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void delete(){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            //注册mysql驱动
            Class.forName(DRIVER);

            //获得一个链接
            connection = DriverManager.getConnection(URL, USE, PWD);

            //创建一个查询
            String sql = "delete from t_user where id= ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"7");
            int i = preparedStatement.executeUpdate();


            connection.commit();
            System.out.println(i);
            //从resultSet获取数据
      //      while (resultSet.next()){
                System.out.println("=================");
//                TUser tUser = new TUser();
//                tUser.setId(resultSet.getInt("id"));
//                tUser.setUserName(resultSet.getString("user_name"));
//                tUser.setRealName(resultSet.getString("real_name"));
//                tUser.setMobile(resultSet.getString("mobile"));
//                tUser.setPositionId(resultSet.getInt("position_id"));

//                System.out.println(tUser);
        //    }

            //关闭流
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e){
        } catch (SQLException se){
        } catch (Exception e){
        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void insert(){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            //注册mysql驱动
            Class.forName(DRIVER);

            //获得一个链接
            connection = DriverManager.getConnection(URL, USE, PWD);

            //创建一个查询
            String sql = "insert into t_user ( email, user_name, sex, mobile, real_name, note, position_id ) " +
                    "values ( ?, ?, ?, ?, ?, ?, ? ) ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"121@qq.com");
            preparedStatement.setString(2,"Alise");
            preparedStatement.setString(3,"1");
            preparedStatement.setString(4,"1532159");
            preparedStatement.setString(5,"jd");
            preparedStatement.setString(6,"wxnote");
            preparedStatement.setString(7,"3");
            int i = preparedStatement.executeUpdate();


            connection.commit();
            System.out.println(i);
            //从resultSet获取数据
            //      while (resultSet.next()){
            System.out.println("=================");
//                TUser tUser = new TUser();
//                tUser.setId(resultSet.getInt("id"));
//                tUser.setUserName(resultSet.getString("user_name"));
//                tUser.setRealName(resultSet.getString("real_name"));
//                tUser.setMobile(resultSet.getString("mobile"));
//                tUser.setPositionId(resultSet.getInt("position_id"));

//                System.out.println(tUser);
            //    }

            //关闭流
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e){
        } catch (SQLException se){
        } catch (Exception e){
        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void insertBatch(){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            //注册mysql驱动
            Class.forName(DRIVER);

            //获得一个链接
            connection = DriverManager.getConnection(URL, USE, PWD);

            //创建一个查询
            String sql1 = "insert into t_user ( email, user_name, sex, mobile, real_name, note, position_id ) " +
                    "values ( ?, ?, ?, ?, ?, ?, ? ) ";
            preparedStatement = connection.prepareStatement(sql1);

            //启动手动提交
            connection.setAutoCommit(false);

            preparedStatement.setString(1,"121@qq.com");
            preparedStatement.setString(2,"Alise");
            preparedStatement.setInt(3,1);
            preparedStatement.setString(4,"1532159");
            preparedStatement.setString(5,"jd");
            preparedStatement.setString(6,"wxnote");
            preparedStatement.setInt(7,3);

            preparedStatement.addBatch();

            preparedStatement.setString(1,"121@qq.com");
            preparedStatement.setString(2,"Ezral");
            preparedStatement.setInt(3,1);
            preparedStatement.setString(4,"323232");
            preparedStatement.setString(5,"伊泽瑞尔");
            preparedStatement.setString(6,"eznote");
            preparedStatement.setInt(7,5);

            preparedStatement.addBatch();
            System.out.println(preparedStatement);
            int[] ints = preparedStatement.executeBatch();
            //返回为执行第一条sql影响的行数 第二条影响的行数
            System.out.println(ints);

            connection.commit();
            //从resultSet获取数据
            //      while (resultSet.next()){
            System.out.println("=================");
//                TUser tUser = new TUser();
//                tUser.setId(resultSet.getInt("id"));
//                tUser.setUserName(resultSet.getString("user_name"));
//                tUser.setRealName(resultSet.getString("real_name"));
//                tUser.setMobile(resultSet.getString("mobile"));
//                tUser.setPositionId(resultSet.getInt("position_id"));

//                System.out.println(tUser);
            //    }

            //关闭流
            resultSet.close();
            preparedStatement.close();
            connection.close();

            connection.setAutoCommit(true);//3,提交完成后回复现场将Auto commit,还原为true,
        } catch (ClassNotFoundException e){
        } catch (SQLException se){
        } catch (Exception e){
        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
