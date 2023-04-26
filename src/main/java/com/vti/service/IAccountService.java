package com.vti.service;

import com.vti.entity.Account;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountFilterForm;
import com.vti.form.AccountUpdateForm;
import com.vti.form.AuthChangePasswordForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IAccountService extends UserDetailsService {
    Page<Account> findAll(Pageable pageable, AccountFilterForm form);

    Account findById(int id);

    void create(AccountCreateForm form);

    void update(AccountUpdateForm form);

    void deleteById(int id);

    boolean existsById(int id);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    Account findByUsername(String username);

    void deleteAll(List<Integer> ids);

    void changePassword(AuthChangePasswordForm form);
}
