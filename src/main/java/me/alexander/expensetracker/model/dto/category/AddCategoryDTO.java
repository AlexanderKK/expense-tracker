package me.alexander.expensetracker.model.dto.category;

import jakarta.validation.constraints.NotBlank;
import me.alexander.expensetracker.validation.category.UniqueCategoryName;

public class AddCategoryDTO {

    private String iconClass;

    @NotBlank(message = "Please choose a category")
    @UniqueCategoryName
    private String name;

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
