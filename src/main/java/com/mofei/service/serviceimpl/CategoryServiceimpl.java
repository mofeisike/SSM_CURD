package com.mofei.service.serviceimpl;

import com.mofei.pojo.Category;
import com.mofei.mapper.CategoryMapper;
import com.mofei.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceimpl implements CategoryService {


    @Autowired
    CategoryMapper categoryMapper;


    @Override
    public List<Category> list() {
        return categoryMapper.list();
    }


    @Override
    public void add(Category category) {
        categoryMapper.add(category);
    }

    @Override
    public void detele(Category category) {
        categoryMapper.delete(category.getId());
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }

    @Override
    public Category get(int id) {
        return categoryMapper.get(id);
    }

    @Override
    public List<Category> getName(String name) {
        return categoryMapper.getName(name);
    }

    @Override
    public List<Category> getNameByObj(Category category) {
        return categoryMapper.getNameByObj(category);
    }

    @Override
    public boolean deteleComments(Integer id) {
        return false;
    }


    @Override
    public List<Category> listbyenrty() {
        return categoryMapper.listbyentry();
    }
}
