package com.mofei.service;


import com.mofei.pojo.Category;

import java.util.List;

public interface CategoryService {

    List<Category> list();


    void add(Category category);

    void detele(Category category);

    void update(Category category);

    Category get(int id);

    //查询
    List<Category> getName(String name);

    //查询
    List<Category> getNameByObj(Category category);

    //删除所有新闻和评论
    boolean deteleComments(Integer id);

    //查询分类产品
    List<Category> listbyenrty();
}
