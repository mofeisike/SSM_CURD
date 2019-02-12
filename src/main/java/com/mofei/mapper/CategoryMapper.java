package com.mofei.mapper;


import com.mofei.pojo.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {

    public int add(@Param("category") Category category);

    public void delete(int id);

    public Category get(int id);

    public int update(@Param("category") Category category);

    public List<Category> list();

    public int total();

    List<Category> getName(@Param("name") String name);

    List<Category> getNameByObj(@Param("category") Category category);

    //删除评论的新闻
    int deleteComment(@Param("id") Integer id);

    //查询产品分类
    List<Category> listbyentry();
}
