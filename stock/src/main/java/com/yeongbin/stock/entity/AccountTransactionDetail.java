package com.yeongbin.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "account_transaction_detail")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransactionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_number")
    private Account account;

    @Column(name = "is_deposit_operation")
    private String isDepositOperation;

    @Column(name = "deposit_money")
    private Long depositMoney;

    @Column(name = "deposit_date")
    private LocalDate depositDate;


    public AccountTransactionDetail(Account account, String isDepositOperation, Long depositMoney, LocalDate depositDate) {
        this.account = account;
        this.isDepositOperation = isDepositOperation;
        this.depositMoney = depositMoney;
        this.depositDate = depositDate;
    }
}
