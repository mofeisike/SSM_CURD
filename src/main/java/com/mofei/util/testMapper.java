package com.mofei.util;

import com.mofei.mapper.DepartmentMapper;
import com.mofei.mapper.EmployeeMapper;
import com.mofei.pojo.Department;
import com.mofei.pojo.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * @author mofeiske
 * @Description: 测试doa层的工作
 * @Date: Create in 2019/2/13 14:17
 */

/*用那个单元测试,spring4自带的*/
@RunWith(SpringJUnit4ClassRunner.class)
/*加载文件*/
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class testMapper {


    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    //批量的SqlSession
    @Autowired
    SqlSession sqlSession;

    @Test
    public void  testCURD(){
        //1、创建SpringIOC容器
        //ApplicationContext ioc = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
        //2、从容器中获取mapper
        //Department bean = ioc.getBean(Department.class);

        /*住荐Spring的项目就可以使用Spring的单元测试，可以自动注入我们需要的组件
        * 1、导入SpringTest模块
        * 2、@ContextConfiguration指定Spring配置文件的位置
        * 3、直接autowired要使用的组件即可
        *
        * */
        System.out.println(departmentMapper);

        Department department = new Department();
        Department departmentone = new Department();
        department.setDeptName("开发部");
        departmentone.setDeptName("服务部");
        departmentMapper.insertSelective(department);
        departmentMapper.insertSelective(departmentone);

//        Employee employee = new Employee();
//        employee.setEmpName("Jack");
//        employee.setGender("M");
//        employee.setEmail("Jack@163.com");
//        employee.setdId(1);
//        employeeMapper.insertSelective(employee);

        //批量操作
        EmployeeMapper batchEmployeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 10; i++) {
            String uuid = UUID.randomUUID().toString().substring(0, 5);
            batchEmployeeMapper.insertSelective(new Employee(null,uuid,"M",uuid+"@163.com",1));
        }
        System.out.println("sueecces");

    }

}
