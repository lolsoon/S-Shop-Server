package com.vti.service;

import com.vti.entity.Category;
import com.vti.form.CategoryCreateForm;
import com.vti.form.CategoryFilterForm;
import com.vti.form.CategoryUpdateForm;
import com.vti.repository.ICategoryRepository;
import com.vti.specification.CategorySpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Page<Category> findAll(Pageable pageable, CategoryFilterForm form) {
        return repository.findAll(CategorySpecification.buildWhere(form), pageable);
    }

    @Override
    public Category findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void create(CategoryCreateForm form) {
        repository.save(mapper.map(form, Category.class));
    }

    @Override
    public void update(CategoryUpdateForm form) {
        repository.save(mapper.map(form, Category.class));
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll(List<Integer> ids) {
        repository.deleteAllById(ids);
    }
}
