package com.vti.service;

import com.vti.entity.Category;
import com.vti.form.CategoryCreateForm;
import com.vti.form.CategoryFilterForm;
import com.vti.form.CategoryUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryService {
    Page<Category> findAll(Pageable pageable, CategoryFilterForm form);

    Category findById(int id);

    void create(CategoryCreateForm form);

    void update(CategoryUpdateForm form);

    void deleteById(int id);

    void deleteAll(List<Integer> ids);
}
