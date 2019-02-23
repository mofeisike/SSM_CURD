package com.mofei.servlet;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = {},loadOnStartup = 2)
public class WebPathinitServlet extends HttpServlet
{
    @Override
    public void init(ServletConfig config) throws ServletException {
        //在整体的上下文当中存储一个ctx的值,用他来引用上下文的路径
        config.getServletContext().setAttribute("ctx",config.getServletContext().getContextPath());
        System.out.println("config ------------------------------------: "+config.toString());
        super.init(config);
    }
}
