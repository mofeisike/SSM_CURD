package com.mofei.service.serviceimpl;

import com.mofei.mapper.DepartmentMapper;
import com.mofei.mapper.EmployeeMapper;
import com.mofei.pojo.Department;
import com.mofei.pojo.Employee;
import com.mofei.service.DepartmentService;
import com.mofei.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mofeiske
 * @Description: ${todo}
 * @Date: Create in 2019/2/12 16:56
 */

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;


    @Override
    public List<Department> getDepts() {
        return departmentMapper.selectByExample(null);
    }
}
