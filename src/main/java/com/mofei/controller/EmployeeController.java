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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author mofeiske
 * @Description: ${todo}
 * @Date: Create in 2019/2/12 16:58
 */

@Controller
public class EmployeeController {

    Logger logger = Logger.getLogger("EmployeeController.class");

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


    //查询单个员工
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Message getEmps(@PathVariable("id") Integer id, Model model){
        //@PathVariable("id")   表示这个id是从链接的路径取得值
        Employee emp = employeeService.getEmp(id);
        return Message.success().add("emp",emp);
    }


    //更新员工,
    /**
     如果直接发送ajax=PUT形式的请求
     封装的数据
     Employee
     [empId=1014，empName=nul1，gender=null，email=null，dId=null

     问题：
     请求体中有数据；
     但是Employee对象封装不上；
     update tbl_emp where emp_id = 1014

     AJAX发送PUT请求引发的血案：
     PUT请求，请求体中的数据，request.getParameter（"empName"）拿不到
     Tomcat-看是PUT不会封装请求体中的数据为map，只有POST形式的请求才封装请求体为map

     添加拦截器:在web.xml里面HttpPutFormContentFilter
     我们要能支持直接发送PUT之类的请求还要封装请求体中的数据
     配置上HttpPutFormContentFilter；
     他的作用；将请求体中的数据解析包装成一个map。
     request被重新包装，request.getParameter（）被重写，就会从自己封装的map中取数据

     员工更新 这里ajax请求直接发put请求而不是post请求,那么所有的参数都会获取不到,因为tomcat只会封装post的数据
                        也就是说request.getParameter("empId")为空,springmvc也无法封装Bean
                        解决方法: 1.发送post方法,通过HiddenHttpMethodFilter
                        2.发送put请求,通过HttpPutFormContentFilter
     */

    @RequestMapping(value = "/emp/{empId}",method = RequestMethod.PUT)
    ///emp/{empId} 这里的empId必须和employee的字段一样,这样才能把这个empId的值自动封装到emploee对象中
    @ResponseBody
    public Message saveEmp(Employee employee){
        //System.out.println(employee);
        logger.info(employee.toString());
        employeeService.updataEmp(employee);
        return Message.success();
    }

    /*
    post方式的修改更新
    @RequestMapping(value = "/emp/{empId}",method = RequestMethod.POST)
    @ResponseBody
    public Message saveEmp(Employee employee){
        //System.out.println(employee);
        logger.info(employee.toString());
        employeeService.updataEmp(employee);
        return Message.success();
    }*/


    @RequestMapping(value = "/emp/delete/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Message delEmpsById(@PathVariable("id") Integer id){
        //@PathVariable("id")  去出路劲的id值
        employeeService.deleteEmp(id);
        return Message.success();
    }

    /**
     * 单个删除 1
     * 批量删除 1-2-3
     */
    @RequestMapping(value = "/emp/deletes/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public Message delEmpsById(@PathVariable("ids") String ids){
        if (ids.contains("-")) {
            String[] str_ids = ids.split("-");
            List<Integer> del_ids = new ArrayList<>();
            //组装id
            for (String id: str_ids) {
                del_ids.add(Integer.parseInt(id));
            }
            employeeService.deleteBatch(del_ids);
        }else {
            Integer id = Integer.parseInt(ids);
            employeeService.deleteEmp(id);
        }
        return Message.success();
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
