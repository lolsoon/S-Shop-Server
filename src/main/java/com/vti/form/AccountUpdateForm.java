package com.vti.form;

import com.vti.entity.Account;
import com.vti.validation.AccountExistsById;
import com.vti.validation.AccountNotExistsByUsername;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AccountUpdateForm {
    @NotNull(message = "{AccountForm.id.NotNull}")
    @AccountExistsById
    private Integer id;

    @NotBlank(message = "{AccountForm.username.NotBlank}")
    @Length(max = 50, message = "{AccountForm.username.Length}")
    @AccountNotExistsByUsername
    private String username;

    @NotBlank(message = "{AccountForm.password.NotBlank}")
    @Length(max = 32, message = "{AccountForm.password.Length}")
    private String password;

    @NotBlank(message = "{AccountForm.firstName.NotBlank}")
    @Length(max = 50, message = "{AccountForm.firstName.Length}")
    private String firstName;

    @NotBlank(message = "{AccountForm.lastName.NotBlank}")
    @Length(max = 50, message = "{AccountForm.lastName.Length}")
    private String lastName;

    @NotNull(message = "{AccountForm.role.NotNull}")
    private Account.Role role;
}
