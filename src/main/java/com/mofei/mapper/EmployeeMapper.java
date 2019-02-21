package com.mofei.mapper;

import com.mofei.pojo.Employee;
import com.mofei.pojo.EmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
    /*条件计数*/
    long countByExample(EmployeeExample example);

    /*条件删除*/
    int deleteByExample(EmployeeExample example);

    /*主键删除*/
    int deleteByPrimaryKey(Integer empId);

    /*全部插入*/
    int insert(Employee record);

    /*选择插入*/
    int insertSelective(Employee record);

    /*条件计数*/
    List<Employee> selectByExample(EmployeeExample example);

    /*主键查询*/
    Employee selectByPrimaryKey(Integer empId);

    /*条件计数*/
    List<Employee> selectByExampleWithDept(EmployeeExample example);

    /*主键查询*/
    Employee selectByPrimaryKeyWithDept(Integer empId);

    /*条件选择的更新*/
    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    /*条件all更新*/
    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    /*主键更新字段*/
    int updateByPrimaryKeySelective(Employee record);

    /*主键all更新*/
    int updateByPrimaryKey(Employee record);
}