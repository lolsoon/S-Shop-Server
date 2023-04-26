package com.vti.controller;

import com.vti.dto.ProfileDTO;
import com.vti.entity.Account;
import com.vti.form.AccountCreateForm;
import com.vti.form.AuthChangePasswordForm;
import com.vti.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Validated
@RestController
@RequestMapping(value = "api/v1/auth")
public class AuthController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IAccountService service;


    @GetMapping("/login")
    public ProfileDTO login(Principal principal) {
        String username = principal.getName();
        Account account = service.findByUsername(username);
        return mapper.map(account, ProfileDTO.class);
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid AccountCreateForm form) {
        service.create(form);
    }

    @PutMapping("/change-password")
    public void changePassword(@RequestBody @Valid AuthChangePasswordForm form) {
        service.changePassword(form);
    }
}
