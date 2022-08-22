package com.yeongbin.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRegisterDto {
    @NotBlank
    private String accountNumber;

    private Long userId;
}
