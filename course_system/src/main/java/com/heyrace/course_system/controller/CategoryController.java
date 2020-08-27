package com.heyrace.course_system.controller;

import com.heyrace.beans.Category;
import com.heyrace.beans.RespBean;
import com.heyrace.dto.CategoryDto;
import com.heyrace.dto.CategoryDto2;
import com.heyrace.service.CategoryService;
import com.heyrace.utils.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@CacheConfig(cacheNames = "cache1")
@RequestMapping("/category")
public class CategoryController {
    @Resource
    CategoryService categoryService;

    @GetMapping("/list")
    public List<Category> list() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/insert")
    public RespBean insert(@RequestBody @Valid CategoryDto categoryDto) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto,category);
        category.setId(UuidUtil.getUuid());
        int res = categoryService.insertCategory(category);
        if (res>0) return RespBean.success(category);
        else {
            return RespBean.error("插入失败");
        }

    }

    @PostMapping("/delete")
    public RespBean delete(@RequestBody @Valid CategoryDto2 categoryDto2) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto2,category);
        int res = categoryService.deleteCategory(category);
        if (res == 0) return RespBean.success("数据库中没有该数据",null);
        else return RespBean.success("删除成功",null);
    }
}
