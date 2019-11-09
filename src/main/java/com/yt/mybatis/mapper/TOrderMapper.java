package com.yt.mybatis.mapper;

import com.yt.mybatis.entity.TOrder;
import org.apache.ibatis.annotations.Param;

public interface TOrderMapper {

    TOrder selectByKey(@Param("orderId") Integer orderId);
}
