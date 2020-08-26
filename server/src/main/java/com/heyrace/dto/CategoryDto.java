package com.heyrace.dto;

public class CategoryDto {
    private String id;

    private String courseId;

    private String name;

    public CategoryDto(String id, String courseId, String name) {
        this.id = id;
        this.courseId = courseId;
        this.name = name;
    }

    public CategoryDto() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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