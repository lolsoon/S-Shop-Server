package com.vti.dto;

import com.vti.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AccountDTO {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private Account.Role role;
    private LocalDate createdDate;
    private LocalDateTime updatedAt;
}
