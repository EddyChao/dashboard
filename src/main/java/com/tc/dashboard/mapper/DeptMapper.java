package com.tc.dashboard.mapper;

import com.tc.dashboard.bean.Department;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptMapper {


    @Select("select * from department")
    List<Department> findAllDept();

    @Select("select * from department where id = #{id}")
    Department findDeptById(Integer id);
}
