package com.yeongbin.stock.service;

import com.yeongbin.stock.dto.AccountTransactionDetailRegisterDto;
import com.yeongbin.stock.entity.Account;
import com.yeongbin.stock.entity.AccountTransactionDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountTransactionDetailServiceTest {
    @Autowired
    AccountTransactionDetailService accountTransactionDetailService;

    AccountTransactionDetailRegisterDto create(){
        AccountTransactionDetailRegisterDto accountTransactionDetailRegisterDto = new AccountTransactionDetailRegisterDto();
        accountTransactionDetailRegisterDto.setAccountId("1000-40");
        accountTransactionDetailRegisterDto.setDepositMoney(50000L);
        accountTransactionDetailRegisterDto.setIsDepositOperation("Y");

        return accountTransactionDetailRegisterDto;
    }

    @DisplayName("")
    @Test
    void registerAccountTransactionDetail() {
        AccountTransactionDetailRegisterDto accountTransactionDetailRegisterDto = create();

        AccountTransactionDetail accountTransactionDetail =
                accountTransactionDetailService.registerAccountTransactionDetail(accountTransactionDetailRegisterDto);

        Assertions.assertEquals(accountTransactionDetail.getAccount().getAccountNumber(), accountTransactionDetailRegisterDto.getAccountId());
        Assertions.assertEquals(accountTransactionDetail.getIsDepositOperation(), accountTransactionDetailRegisterDto.getIsDepositOperation());
        Assertions.assertEquals(accountTransactionDetail.getDepositMoney(), accountTransactionDetailRegisterDto.getDepositMoney());
    }

    @DisplayName("계좌내역 전체 조회 성공 테스트")
    @Test
    void getAllAccountTransactionDetailListSuccessTest() {
        List<AccountTransactionDetail> accountTransactionDetailList =
                accountTransactionDetailService.getAllAccountTransactionDetailList();
        Assertions.assertEquals(accountTransactionDetailList.size(),500);
    }
}