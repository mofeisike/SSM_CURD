package com.mofei.controller;

import com.mofei.pojo.Department;
import com.mofei.pojo.Message;
import com.mofei.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author mofeiske
 * @Description: 处理和部分有关的
 * @Date: Create in 2019/2/12 16:58
 */
@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    /*
    * 返回所有的部分信息
    */
    @RequestMapping("/depts")
    @ResponseBody
    public Message getDepts(){

        List<Department> list = departmentService.getDepts();
        return Message.success().add("dept",list);
    }

}
