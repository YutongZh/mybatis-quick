package com.yt.mybatis.mapper;

import com.yt.mybatis.entity.TUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserMapper {

    TUser getTUserByPrimaryKey(Integer id);

    TUser getTUser1(@Param("userName") String userName, @Param("id") Integer id);

    TUser getTUser2(TUser user);

    TUser getTUser3(TUser user);

    Integer insertTUser4(TUser user);

    Integer insertTUser5(TUser user);

    TUser queryByWhere (@Param("userName") String userName, @Param("id") Integer id, @Param("sex") Integer sex);

    Integer updateBySet(@Param("id") Integer id, @Param("userName") String userName, @Param("email") String email, @Param("positionId") Integer positionId);

    Integer insertByTrim(TUser tUser);

    List<TUser> selectByForEach(String[] userName);

    List<TUser> selectByForEachList(List<Integer> ids);

    Integer batchInsert(List<TUser> userList);

    TUser selectByChoose(TUser tUser);
}
