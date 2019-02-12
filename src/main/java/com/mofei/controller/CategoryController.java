package com.mofei.controller;


import com.mofei.pojo.Category;
import com.mofei.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/news")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/listCategory")
    public ModelAndView listCategory(){
        List<Category> cs = categoryService.list();
        ModelAndView modelAndView = new ModelAndView("listCategory","cs",cs);

        //放入参数
        //modelAndView.addObject("cs",cs);
        //放入jsp路劲
        //modelAndView.setViewName("listCategory");

        return modelAndView;
    }

    @RequestMapping("/addCategory")
    public ModelAndView addCategory(Category category){
        category.setCreateDate(new Date());
        categoryService.add(category);
        ModelAndView modelAndView = new ModelAndView("redirect:/news/listCategory");
        return modelAndView;
    }


    @RequestMapping("/deleteCategory")
    public ModelAndView deleteCategory(Category category){
        categoryService.detele(category);
        ModelAndView modelAndView = new ModelAndView("redirect:/news/listCategory");
        return modelAndView;
    }

    @RequestMapping("/deleteComments/{id}")
    public ModelAndView deleteComments(@PathVariable("id") Integer id ,Model model){
        //删除新闻,还要删除所有评论
        boolean suc = categoryService.deteleComments(id);
        model.addAttribute("deletesuc",suc);
        List<Category> cs = categoryService.list();
        ModelAndView modelAndView = new ModelAndView("listCategory","cs",cs);
        return modelAndView;
    }

    @RequestMapping("/editCategory")
    public ModelAndView editCategory(Category category){
        System.out.println(category.getId());
        System.out.println(categoryService.get(category.getId()));
        ModelAndView modelAndView = new ModelAndView("editCategory","cc",categoryService.get(category.getId()));
        return modelAndView;
    }

    @RequestMapping("/updateCategory")
    public ModelAndView updateCategory(Category category){
        categoryService.update(category);
        ModelAndView modelAndView = new ModelAndView("redirect:/news/listCategory");
        return modelAndView;
    }

    @RequestMapping("/getCategory/name")
    public ModelAndView getCategory(@RequestParam("name") String name){
        List<Category> list = categoryService.getName(name);
//        category.setName(name);
//        List<Category> list = categoryService.getNameByObj(category);
        ModelAndView mav = new ModelAndView();
        if (!list.isEmpty()){
            mav.addObject("name",name);
            mav.addObject("cs",list);
            mav.setViewName("/listCategory");
            System.out.println("yes");
        }else {
            mav.addObject("name",name);
            mav.addObject("warn","空值");
            mav.setViewName("/listCategory");
            System.out.println("no");
        }
        System.out.println(name);
        System.out.println(list.size());
        return mav;
    }

}
