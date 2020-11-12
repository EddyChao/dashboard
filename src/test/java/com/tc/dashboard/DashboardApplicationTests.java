package com.tc.dashboard;

import com.tc.dashboard.bean.Department;
import com.tc.dashboard.bean.Employee;
import com.tc.dashboard.bean.User;
import com.tc.dashboard.mapper.DeptMapper;
import com.tc.dashboard.mapper.EmployeeMapper;
import com.tc.dashboard.mapper.RegisterMapper;
import com.tc.dashboard.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;

@SpringBootTest
class DashboardApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    DeptMapper deptMapper;
    @Autowired
    RegisterMapper registerMapper;
//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    PasswordEncoder md5PassWordEncoder;
    @Test
    void contextLoads() {
        Employee emps = employeeMapper.findEmpById(1);
        System.out.println( emps);
        emps.setLastName("ll");
        emps.setGender(0);
        employeeMapper.updateEmp(emps);
        System.out.println( emps);
    }

    @Test
    void test01(){
        User roles = userMapper.userLogin("aa", "111");
        System.out.println(roles);
    }

    @Test
    void test02(){
        Department department = new Department(1,"11");
        Employee employee = new Employee(null,"1","2", 1, department,new Date());
        employeeMapper.insertEmp(employee);
    }

    @Test
    void test03(){
        List<Department> allDept = deptMapper.findAllDept();
        for (Department department : allDept) {
            System.out.println(department);
        }
    }

    @Test
    void test04(){
        User user = new User();
        user.setUsername("dd");
        user.setPassword("11");
        String encode = md5PassWordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        registerMapper.register(user);
    }

    @Test
    void test05(){
        Employee employee = new Employee();
        Department department = new Department(1,null);
        employee.setLastName("la");
        employee.setGender(1);
        employee.setDepartments(department);
    }

}
