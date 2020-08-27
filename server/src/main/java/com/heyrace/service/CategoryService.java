package com.heyrace.service;

import com.heyrace.beans.Category;
import com.heyrace.dto.CategoryDto;
import com.heyrace.mappers.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    public List<Category> getAllCategories() {
        List<Category> allCategories = categoryMapper.getAllCategories();
        return allCategories;
    }

    public Integer insertCategory(Category category) {
        Integer res = categoryMapper.insertSelective(category);
        return res;
    }

    public Integer deleteCategory(Category category) {
        return categoryMapper.deleteByPrimaryKey(category.getId());
    }
}
