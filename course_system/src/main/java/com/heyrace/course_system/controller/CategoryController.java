package com.heyrace.course_system.controller;

import com.heyrace.beans.Category;
import com.heyrace.beans.RespBean;
import com.heyrace.service.CategoryService;
import com.heyrace.utils.UuidUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    CategoryService categoryService;

    @GetMapping("/list")
    List<Category> list() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/insert")
    RespBean insert(@RequestBody @Valid Category category) {
        category.setId(UuidUtil.getUuid());
        int res = categoryService.insertCategory(category);
        if (res>0) return RespBean.success(category);
        else {
            return RespBean.error("插入失败");
        }

    }

    @PostMapping("/delete")
    RespBean delete(@RequestBody @Valid Category category) {

        int res = categoryService.deleteCategory(category);
        if (res == 0) return RespBean.success("数据库中没有该数据",null);
        else return RespBean.success("删除成功",null);

    }
}
