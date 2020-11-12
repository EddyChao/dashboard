package com.tc.dashboard.mapper;

import com.tc.dashboard.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterMapper {

    @Insert("insert into user(username,password) values(#{username},#{password}) ")
    void register(User user);
}
