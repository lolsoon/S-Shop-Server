package com.vti.controller;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountFilterForm;
import com.vti.form.AccountUpdateForm;
import com.vti.service.IAccountService;
import com.vti.validation.AccountExistsById;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "api/v1/accounts")
public class AccountController {
    @Autowired
    private IAccountService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public Page<AccountDTO> findAll(Pageable pageable, AccountFilterForm form) {
        Page<Account> accounts = service.findAll(pageable, form);
        List<AccountDTO> accountDTOs = mapper.map(
                accounts.getContent(),
                new TypeToken<List<AccountDTO>>() {}.getType()
        );
        return new PageImpl<>(accountDTOs, pageable, accounts.getTotalElements());
    }


    @GetMapping("/{id}")
    public AccountDTO findById(@PathVariable("id") @AccountExistsById int id) {
        Account account = service.findById(id);
        return mapper.map(account, AccountDTO.class);
    }

    @PostMapping
    public void create(@RequestBody @Valid AccountCreateForm form) {
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") @AccountExistsById int id, @RequestBody @Valid AccountUpdateForm form) {
        form.setId(id);
        service.update(form);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") @AccountExistsById int id) {
        service.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll(@RequestBody List<@AccountExistsById Integer> ids) {
        service.deleteAll(ids);
    }
}
