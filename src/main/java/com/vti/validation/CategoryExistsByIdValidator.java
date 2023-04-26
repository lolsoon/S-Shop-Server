package com.vti.validation;

import com.vti.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryExistsByIdValidator implements ConstraintValidator<CategoryExistsById, Integer> {
    @Autowired
    private ICategoryRepository repository;

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return repository.existsById(id);
    }
}
