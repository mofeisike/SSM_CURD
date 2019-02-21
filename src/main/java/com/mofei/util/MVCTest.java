package com.mofei.util;

import com.github.pagehelper.PageInfo;
import com.mofei.pojo.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.EmptyStackException;
import java.util.List;

/**
 * @author mofeiske
 * @Description: 使用spring测试模块的测试请求功能，测试curd请求的正确性|
 * @Date: Create in 2019/2/13 19:51
 */

/*用那个单元测试,spring4自带的*/
@RunWith(SpringJUnit4ClassRunner.class)
/*加载文件*/
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
/*自动注入WEb*/
@WebAppConfiguration
public class MVCTest {

    // 传入Springmyc的ioc
    // 自动注入ApplicationContext ,web由上面的注入

    @Autowired
    WebApplicationContext webApplicationContext;

    // 虚拟mVc请求，获取到处理结果
    MockMvc mockMvc;

    //每次都初始化
    @Before
    public void initMockMvc(){
        //模拟mvc请求发送
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void testPage() throws Exception {
        /*模拟 一个 get (/emps) 请求 带入参数 pn 值 1 获取那个返回值 */
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1")).andReturn();

        //请求成功以后，请求域中会有pageInfo；我们可以取出pageInfo进行验证
        MockHttpServletRequest mvcResultRequest = mvcResult.getRequest();

        PageInfo pageinfo = (PageInfo) mvcResultRequest.getAttribute("pageinfo");
        System.out.println(pageinfo);

        System.out.println("当前页码"+pageinfo.getPageNum());
        System.out.println("总页码"+pageinfo.getPages());
        System.out.println("总记录数"+pageinfo.getTotal());
        System.out.println("在页面连续显示的页面");

        int[] navigatepageNums = pageinfo.getNavigatepageNums();
        for (int i : navigatepageNums) {
            System.out.println(" "+i);
        }

        List<Employee> list = pageinfo.getList();
        for (Employee e : list) {
            System.out.println("id :"+e.getEmpId() +" name :"+e.getEmpName());
        }


    }


}
