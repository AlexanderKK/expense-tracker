package me.alexander.expensetracker.model.dto.category;

import jakarta.validation.constraints.NotNull;
import me.alexander.expensetracker.validation.category.ExistingCategory;

public class CategoryDTO {

    @NotNull(message = "Please choose a category")
    @ExistingCategory
    private Long id;

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
