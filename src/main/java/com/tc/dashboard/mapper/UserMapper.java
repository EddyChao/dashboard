package com.tc.dashboard.mapper;

import com.tc.dashboard.bean.Role;
import com.tc.dashboard.bean.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    @Select("select * from user where username = #{username} and password = #{password}")
    @Results(value = {
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(column = "role", property = "role",
                one = @One(select = "com.tc.dashboard.mapper.UserMapper.findRolesById",fetchType = FetchType.EAGER)
            )
    })
    User userLogin(String username, String password);


    @Select("select * from role where id = #{id}")
    List<Role> findRolesById(Integer id);

    @Select("select * from user where username = #{username}")
    @Results(value = {
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "role", column = "role",
            one = @One(select = "com.tc.dashboard.mapper.UserMapper.findRolesById", fetchType = FetchType.EAGER)
            )
    })
    User findUserByName(String username);
}
