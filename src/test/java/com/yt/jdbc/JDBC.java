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
                TUser tUser = new TUser();
                tUser.setId(resultSet.getInt("id"));
                tUser.setUserName(resultSet.getString("user_name"));
                tUser.setRealName(resultSet.getString("real_name"));
                tUser.setMobile(resultSet.getString("mobile"));
                tUser.setPositionId(resultSet.getInt("position_id"));

                System.out.println(tUser);
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
}
