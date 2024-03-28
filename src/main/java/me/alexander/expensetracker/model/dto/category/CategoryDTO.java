package me.alexander.expensetracker.model.dto.category;

import jakarta.validation.constraints.NotBlank;

public class CategoryDTO {

    private Long id;

    @NotBlank(message = "Please choose a category")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
