package com.yeongbin.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransactionDetailRegisterDto {
    private Long id;

    private String accountId;

    private String isDepositOperation;

    private Long depositMoney;

    private LocalDate depositDate;
}
