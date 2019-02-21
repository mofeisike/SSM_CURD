package com.mofei.service.serviceimpl;

import com.mofei.mapper.EmployeeMapper;
import com.mofei.pojo.Employee;
import com.mofei.pojo.EmployeeExample;
import com.mofei.pojo.EmployeeExample.Criteria;
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
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }

    @Override
    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    @Override
    public boolean checkUser(String empName) {
        /**
         * version: 1.0
         * description: 检验用户名
         * @param [empName]
         * @return boolean
         * date: 2019/2/18 21:23
         * autor: mofeiske
         */
        //查询同名的数量,1没有重名,2重名


        //创建条件,添加条件到实例
        EmployeeExample example = new EmployeeExample();
        Criteria exampleCriteria = example.createCriteria();
        exampleCriteria.andEmpNameEqualTo(empName);

        long count = employeeMapper.countByExample(example);
        return count == 0;
    }

    /**
     * version: 1.0
     * description: 按照员工id查询员工
     * @param
     * @return
     * date: 2019/2/21 21:58
     * autor: mofeiske
     */
    @Override
    public Employee getEmp(Integer id) {
//        employeeMapper.selectByPrimaryKeyWithDept();
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }


}
