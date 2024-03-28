package me.alexander.expensetracker.model.dto.category;

import jakarta.validation.constraints.NotBlank;
import me.alexander.expensetracker.validation.UniqueCategoryName;

public class AddCategoryDTO {

    @NotBlank(message = "Please choose a category")
    @UniqueCategoryName
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
