package me.alexander.expensetracker.model.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoryDTO {

    @NotBlank(message = "Please choose a category")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
