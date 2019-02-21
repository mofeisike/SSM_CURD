package com.mofei.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mofei.pojo.Employee;
import com.mofei.pojo.Message;
import com.mofei.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import sun.applet.resources.MsgAppletViewer;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mofeiske
 * @Description: ${todo}
 * @Date: Create in 2019/2/12 16:58
 */

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /*
    URI：
    /emp/{id}   GET查询员工
    /emp        POST保存员工
    /emp/{id}   PUT修改员工
    /emp/{id}   DELETE删除员工
     */


    @RequestMapping("/emps")
    @ResponseBody
    public Message getEmpsWithJson(@RequestParam(value = "pn",defaultValue = "1") Integer pn , Model model){
        PageHelper.startPage(pn,7);
        List<Employee> emps = employeeService.getAll();
        PageInfo page = new PageInfo(emps);
        /*构造一个新的对象,在Message的Map里面添加Map对象*/
        return Message.success().add("pageinfo",page);
    }

    //保存员工,
    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    @ResponseBody
    public Message saveEmp(@Valid Employee employee, BindingResult result){
        //BindingResult 校验结果
        if (result.hasErrors()){
            //校验失败, 应该返回失败，在模态框中显示校验失败的错误信息
            Map<String ,Object> map = new HashMap<>();

            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError:errors) {
                System.out.println("错误的字段"+fieldError.getField());
                System.out.println("错误的信息"+fieldError.getDefaultMessage());
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Message.fail().add("errorFields",map);
        }else{
            employeeService.saveEmp(employee);
        }
        return Message.success();
    }


    //检验用户名是否可用
    @ResponseBody
    @RequestMapping("/checkuser")
    public Message checkuser(@RequestParam("empName") String empName){
        boolean b = employeeService.checkUser(empName);

        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u4E00-\\u9FA5]{2,5})";
        //string自带的方法 校验正则表达式
        if (!empName.matches(regx)) {
            System.out.println(empName);
            System.out.println(empName.matches(regx));
            return Message.fail().add("value_msg","用户名必须是6-16 位的字母或者2-5位的中文");
        }

        //重复名校验
        if (b){
            return Message.success();
        }else {
            return Message.fail().add("value_msg","用户名不可用");
        }
    }



    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Message getEmps(@PathVariable("id") Integer id, Model model){
        //@PathVariable("id")   表示这个id是从链接的路径取得值
        Employee emp = employeeService.getEmp(id);
        return Message.success().add("emp",emp);
    }



    //    @RequestMapping("/emps")
//    public String getEmps(@RequestParam(value = "pn",defaultValue = "1") Integer pn , Model model){
//        /* 这个pn没有值,默认是1 */
//        //获取第1页，10条内容，默认查询总数count
//        PageHelper.startPage(pn,3);
//        List<Employee> emps = employeeService.getAll();
//        //用PageInfo对结果进行包装,PageInfo包含了非常全面的分页属性,和封装的数据
//        //使用它的构造器 , 传入5,显示连续页码数量
//        PageInfo page = new PageInfo(emps);
//
//        model.addAttribute("pageinfo",page);
//        return "list";
//    }

}
