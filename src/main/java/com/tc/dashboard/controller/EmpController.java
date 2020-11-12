package com.tc.dashboard.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tc.dashboard.bean.Department;
import com.tc.dashboard.bean.Employee;
import com.tc.dashboard.mapper.DeptMapper;
import com.tc.dashboard.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller()
public class EmpController {

    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    DeptMapper deptMapper;

    @GetMapping("/emps/list")
    public String empQuery(){
        return "emp/list";
    }



    @RequestMapping("/emps/all/{page}")
    public ModelAndView empList(@PathVariable("page") Integer page){
        page = page == 0?1:page;
        ModelAndView mv = new ModelAndView();
        List<Employee> employees = employeeMapper.findAllEmp();
        mv.addObject("emps", employees);
        mv.setViewName("emp/list");
        PageHelper.startPage(page,5);
        PageInfo<Employee> pageInfo = new PageInfo<Employee>(employeeMapper.findAllEmp());
        System.out.println(pageInfo);
        mv.addObject("pages", pageInfo);
        return mv;
    }

    @GetMapping("/emp")
    public ModelAndView empAdd(){
        List<Department> allDept = deptMapper.findAllDept();
        ModelAndView mv = new ModelAndView();
        mv.addObject("depts", allDept).setViewName("emp/add");
        return mv;
    }


    @PostMapping("/emp")
    public String empAddDb(Employee employee){
        employeeMapper.insertEmp(employee);
        return "forward:/emps/all/0";
    }


    @GetMapping("/emp/{id}")
    public ModelAndView empUpd(@PathVariable("id")Integer id){
        Employee emp = employeeMapper.findEmpById(id);
        List<Department> depts = deptMapper.findAllDept();
        ModelAndView mv = new ModelAndView();
        mv.addObject("emp", emp);
        mv.addObject("depts", depts).setViewName("emp/edit");
        return mv;
    }

    @PostMapping("/emp/update/{id}")
    public String empUpdDb(Employee employee, @PathVariable("id") Integer id){
        employee.setId(id);
        employeeMapper.updateEmp(employee);
        System.out.println(employee.getBirth());
        return "forward:/emps/all/0";
    }

    @GetMapping("/emp/del/{id}")
    public String empDel(@PathVariable("id") Integer id){
        employeeMapper.delEmp(id);
        return "forward:/emps/all/0";

    }


}
