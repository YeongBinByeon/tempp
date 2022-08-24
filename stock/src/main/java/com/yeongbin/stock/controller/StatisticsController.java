package com.yeongbin.stock.controller;

import com.yeongbin.stock.entity.Account;
import com.yeongbin.stock.entity.AccountTransactionDetail;
import com.yeongbin.stock.entity.User;
import com.yeongbin.stock.repository.AccountRepository;
import com.yeongbin.stock.repository.UserRepository;
import com.yeongbin.stock.service.AccountService;
import com.yeongbin.stock.service.StatisticsService;
import com.yeongbin.stock.service.UserService;
import lombok.RequiredArgsConstructor;

import lombok.extern.java.Log;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Log
@RestController
@RequiredArgsConstructor
public class StatisticsController {
    private final AccountService accountService;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final StatisticsService statisticsService;
    /*
    // 사용자를 입력받아, 사용자의 계좌별 예치금을 출력하시오
    // 예치금 : 입금앱 - 출금액
    [Account(accountNumber=1000-23, user=com.yeongbin.stock.entity.User@439e6e9c, accountTransactionDetailList=[com.yeongbin.stock.entity.AccountTransactionDetail@72f23ede, com.yeongbin.stock.entity.AccountTransactionDetail@2a3f328a, com.yeongbin.stock.entity.AccountTransactionDetail@72d6da7b, com.yeongbin.stock.entity.AccountTransactionDetail@47f218a9, com.yeongbin.stock.entity.AccountTransactionDetail@2495da47, com.yeongbin.stock.entity.AccountTransactionDetail@776d173d, com.yeongbin.stock.entity.AccountTransactionDetail@33ac1ea9, com.yeongbin.stock.entity.AccountTransactionDetail@644c6f25, com.yeongbin.stock.entity.AccountTransactionDetail@6e81c609, com.yeongbin.stock.entity.AccountTransactionDetail@714af01b])
    , Account(accountNumber=1000-29, user=com.yeongbin.stock.entity.User@439e6e9c, accountTransactionDetailList=[com.yeongbin.stock.entity.AccountTransactionDetail@45a3acce, com.yeongbin.stock.entity.AccountTransactionDetail@63cf5649, com.yeongbin.stock.entity.AccountTransactionDetail@46241d74, com.yeongbin.stock.entity.AccountTransactionDetail@77d87768, com.yeongbin.stock.entity.AccountTransactionDetail@50086233, com.yeongbin.stock.entity.AccountTransactionDetail@645f3c6d, com.yeongbin.stock.entity.AccountTransactionDetail@601a091f, com.yeongbin.stock.entity.AccountTransactionDetail@5e347354, com.yeongbin.stock.entity.AccountTransactionDetail@4d3e9b5b, com.yeongbin.stock.entity.AccountTransactionDetail@501f7ac5])
    , Account(accountNumber=1000-38, user=com.yeongbin.stock.entity.User@439e6e9c, accountTransactionDetailList=[com.yeongbin.stock.entity.AccountTransactionDetail@24f3061d, com.yeongbin.stock.entity.AccountTransactionDetail@f119914, com.yeongbin.stock.entity.AccountTransactionDetail@17bff5d, com.yeongbin.stock.entity.AccountTransactionDetail@69a8e6f0, com.yeongbin.stock.entity.AccountTransactionDetail@55d7c636, com.yeongbin.stock.entity.AccountTransactionDetail@568d3ca1, com.yeongbin.stock.entity.AccountTransactionDetail@852a03a, com.yeongbin.stock.entity.AccountTransactionDetail@26e358a6, com.yeongbin.stock.entity.AccountTransactionDetail@4d6ecc3a, com.yeongbin.stock.entity.AccountTransactionDetail@72399902])]
     */
    @GetMapping(value = "/deposit-by-acounts")
    public List<Map.Entry<String, Long>> getAccountsDeposits(long userId){
        return statisticsService.getAccountsDeposits(userId);
    }

    @GetMapping(value = "/average-deposit")
    public List<Map.Entry<String, Long>> getAgeAverageDeposits(){
        return statisticsService.getAgeAverageDeposits();
    }

    @GetMapping(value = "/sum-deposit")
    public long getYearSumDeposits(int year){
        return statisticsService.getYearSumDeposits(year);
    }
}