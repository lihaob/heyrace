package com.heyrace.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class CategoryDto {
    @NotBlank
    @Length(min=4,max=8,message = "字符长度必须为4-8")
    private String courseId;

    @NotBlank
    @Length(min=1,max=12,message = "字符长度必须为1-12")
    private String name;

    public CategoryDto(@NotBlank @Length(min = 4, max = 8, message = "字符长度必须为4-8") String courseId, @NotBlank @Length(min = 4, max = 12, message = "字符长度必须为4-12") String name) {
        this.courseId = courseId;
        this.name = name;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}