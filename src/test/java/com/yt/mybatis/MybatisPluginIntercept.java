package com.yt.mybatis;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.jdbc.PreparedStatementLogger;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

/**
 * 需求：定义一个阈值，当查询操作运行时间超过这个阈值记录日志供运维人员定位慢查询
 */

//签名（需要拦截的方法）
@Intercepts({@Signature(type = StatementHandler.class, method = "query", args={Statement.class, ResultHandler.class})})
public class MybatisPluginIntercept implements Interceptor {

    private Long threshold;

    /**
     *  执行拦截逻辑的方法
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Long start = System.currentTimeMillis();
        Object proceed = invocation.proceed();
        Long end = System.currentTimeMillis();
        Long showTime = end - start;
        if (showTime > threshold) {
            Object[] args = invocation.getArgs();
            Statement statement = (Statement)args[0];
            //mybatis 反射模块
            MetaObject metaObject = SystemMetaObject.forObject(statement);
            PreparedStatementLogger preparedStatementLogger = (PreparedStatementLogger)metaObject.getValue("h");
            PreparedStatement preparedStatement = preparedStatementLogger.getPreparedStatement();
            System.out.println("SQL语句为：" + preparedStatement.toString() + "; 执行时间为：" + showTime);
        }
        return proceed;
    }

    /**
     * 对拦截对象生成一个代理对象
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 读取在plugin中设置的参数
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        this.threshold = Long.parseLong(properties.getProperty("threshold"));
    }
}
