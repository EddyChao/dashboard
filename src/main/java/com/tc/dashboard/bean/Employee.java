package com.tc.dashboard.bean;


import java.util.Date;

//员工

public class Employee {
    private Integer id;
    private String lastName;
    private String email;
    private Integer gender;//0 女 1 男

    private Department departments;
    private Date birth;

    public Employee() {
    }


    public Employee(Integer id, String lastName, String email, Integer gender, Department departments, Date birth) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.departments = departments;
        this.birth = birth;
    }


    public Department getDepartments() {
        return departments;
    }

    public void setDepartments(Department departments) {
        this.departments = departments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }




    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", departments=" + departments +
                ", birth=" + birth +
                '}';
    }
}
