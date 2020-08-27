package com.heyrace.dto;

public class CategoryDto {
    private String courseId;

    private String name;

    public CategoryDto(String courseId, String name) {
        this.courseId = courseId;
        this.name = name;
    }

    public CategoryDto() {
        super();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}