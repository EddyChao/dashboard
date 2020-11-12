package com.tc.dashboard.mapper;

import com.tc.dashboard.bean.Department;
import com.tc.dashboard.bean.Employee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper {


    @Select("select * from employee")
    @Results(value = {
            @Result(property = "lastName", column = "lastName"),
            @Result(property = "email", column = "email"),
            @Result(property = "gender", column = "gender"),
            @Result(column = "department", property = "departments",
                one = @One(select = "com.tc.dashboard.mapper.EmployeeMapper.findDeptById", fetchType = FetchType.EAGER)
            )
    })
    List<Employee> findAllEmp();

    @Select("select * from department where id = #{id}")
    Department findDeptById(Integer id);

    @Insert("insert into employee(id,lastName,email,gender,department,birth) values(null,#{lastName},#{email},#{gender},#{departments.id},#{birth})")
    void insertEmp(Employee employee);




    @Update("update employee set lastName = #{lastName}, gender = #{gender}, email = #{email}, department = #{departments.id}, birth = #{birth} where id = #{id}")
    void updateEmp(Employee employee);

    @Select("select * from employee where id = #{id}")
    @Results(value = {
            @Result(property = "lastName", column = "lastName"),
            @Result(property = "email", column = "email"),
            @Result(property = "gender", column = "gender"),
            @Result(column = "department", property = "departments",
                    one = @One(select = "com.tc.dashboard.mapper.EmployeeMapper.findDeptById", fetchType = FetchType.EAGER)
            )
    })
    Employee findEmpById(Integer id);

    @Delete("delete from employee where id = #{id}")
    void delEmp(Integer id);
}
