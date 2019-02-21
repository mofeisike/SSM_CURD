package com.mofei.service;

import com.mofei.mapper.EmployeeMapper;
import com.mofei.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mofeiske
 * @Description: ${todo}
 * @Date: Create in 2019/2/12 16:56
 */

public interface EmployeeService {

    List<Employee> getAll();


    void saveEmp(Employee employee);

    boolean checkUser(String empName);


    Employee getEmp(Integer id);
}
