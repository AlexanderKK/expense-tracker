package me.alexander.expensetracker.model.dto.category;

public class CategoryTransactionsDTO {

    private String categoryName;
    private double totalExpenses;

    public CategoryTransactionsDTO() {}

    public CategoryTransactionsDTO(String categoryName, double totalExpenses) {
        this.categoryName = categoryName;
        this.totalExpenses = totalExpenses;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

}
