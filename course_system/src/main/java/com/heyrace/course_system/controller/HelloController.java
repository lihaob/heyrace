package com.heyrace.course_system.controller;

import com.heyrace.beans.Category;
import com.heyrace.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class HelloController {
    @Resource
    CategoryService categoryService;
    @GetMapping("/hello")
    List<Category> hello() {
        return categoryService.getAllCategories();
    }
}
