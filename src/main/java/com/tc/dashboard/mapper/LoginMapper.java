package com.tc.dashboard.mapper;

import com.tc.dashboard.bean.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper {

    @Select("select * from t_user where username = #{username} and password = #{password}")
    User findForLogin(User user);
}
