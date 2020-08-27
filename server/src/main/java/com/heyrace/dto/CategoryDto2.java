package com.heyrace.dto;

import javax.validation.constraints.NotBlank;

public class CategoryDto2 {
    @NotBlank
    private String id;

    public CategoryDto2() {
    }

    public CategoryDto2(@NotBlank String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
