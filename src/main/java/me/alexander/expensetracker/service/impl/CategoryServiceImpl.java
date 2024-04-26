package me.alexander.expensetracker.service.impl;

import me.alexander.expensetracker.model.dto.category.AddCategoryDTO;
import me.alexander.expensetracker.model.dto.category.CategoryDTO;
import me.alexander.expensetracker.model.dto.category.CategoryTransactionsDTO;
import me.alexander.expensetracker.model.entity.Category;
import me.alexander.expensetracker.model.entity.Transaction;
import me.alexander.expensetracker.repository.CategoryRepository;
import me.alexander.expensetracker.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper mapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(ModelMapper mapper,
                               CategoryRepository categoryRepository) {
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void createCategory(AddCategoryDTO addCategoryDTO) {
        Category category = mapper.map(addCategoryDTO, Category.class);

        categoryRepository.save(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> mapper.map(category, CategoryDTO.class))
                .toList();
    }

    @Override
    public List<CategoryTransactionsDTO> getCategoriesExpenses() {
        List<CategoryTransactionsDTO> categoryDTOs = new ArrayList<>();
        DecimalFormat expensesFormatter = new DecimalFormat("#.##");

        for (Category category : categoryRepository.findAll()) {
            String name = category.getName();

            double totalExpenses = category.getTransactions()
                    .stream()
                    .filter(TransactionServiceImpl::isForCurrentMonth)
                    .mapToDouble(Transaction::getExpense)
                    .sum();

            if(totalExpenses == 0.00) {
                continue;
            }

            double formattedExpenses = Double.parseDouble(expensesFormatter.format(totalExpenses));

            categoryDTOs.add(new CategoryTransactionsDTO(name, formattedExpenses));
        }

        return categoryDTOs;
    }

}
