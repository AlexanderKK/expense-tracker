package me.alexander.expensetracker.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column
    private String icon;

    @NotBlank(message = "Category name should not be empty")
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull(message = "Category date should not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate created = LocalDate.now();

    public String getIcon() {
        return icon;
    }

    public void setIcon(String iconClass) {
        this.icon = iconClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

}
