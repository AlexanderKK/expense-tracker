package me.alexander.expensetracker.model.dto.category;

import jakarta.validation.constraints.NotBlank;
import me.alexander.expensetracker.validation.category.UniqueCategoryName;

public class AddCategoryDTO {

    private String icon;

    @NotBlank(message = "Please choose a category")
    @UniqueCategoryName
    private String name;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
