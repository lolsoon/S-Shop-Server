package com.vti.validation;

import com.vti.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountExistsByIdValidator implements ConstraintValidator<AccountExistsById, Integer> {
    @Autowired
    private IAccountRepository repository;

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return repository.existsById(id);
    }
}
