package com.yeongbin.stock.service;

import com.yeongbin.stock.dto.AccountRegisterDto;
import com.yeongbin.stock.entity.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @DisplayName("계좌 등록 성공 테스트")
    @Test
    void registerAccountSuccessTest() {
        AccountRegisterDto accountRegisterDto = new AccountRegisterDto("1000-40", 2L);
        Account account = accountService.registerAccount(accountRegisterDto);
        Assertions.assertEquals(accountRegisterDto.getAccountNumber(), account.getAccountNumber());
        Assertions.assertEquals(accountRegisterDto.getUserId(), account.getUser().getId());
    }

    @DisplayName("계좌 등록 실패 테스트")
    @Test
    void registerAccountFailTest() {
        AccountRegisterDto accountRegisterDto = new AccountRegisterDto("1000-40", -1L);

        Throwable e = assertThrows(EntityNotFoundException.class, () ->{
            Account account = accountService.registerAccount(accountRegisterDto);
        });
        Assertions.assertEquals(e.getMessage(), "존재하지 않는 User의 계좌 생성을 시도하였습니다.");
    }

    @DisplayName("전체 계좌 조회 성공 테스트")
    @Test
    void getAllAccountListSuccessTest() {
        List<Account> accountList = accountService.getAllAccountList();
        Assertions.assertEquals(accountList.size(),50);
    }
}