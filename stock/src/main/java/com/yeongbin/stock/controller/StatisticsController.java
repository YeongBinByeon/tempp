package com.yeongbin.stock.controller;

import com.yeongbin.stock.dto.UserDepositDto;
import com.yeongbin.stock.entity.Account;
import com.yeongbin.stock.entity.AccountTransactionDetail;
import com.yeongbin.stock.entity.User;
import com.yeongbin.stock.repository.AccountRepository;
import com.yeongbin.stock.repository.UserRepository;
import com.yeongbin.stock.service.AccountService;
import com.yeongbin.stock.service.StatisticsService;
import com.yeongbin.stock.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import lombok.extern.java.Log;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Api(tags = {"4. 통계 기능 API"})
@Log
@RequestMapping(value = "/statistics")
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
    @ApiOperation(value = "사용자를 입력받아, 사용자의 계좌별 예치금을 출력하시오")
    @GetMapping(value = "/deposit-by-acounts")
    public List<Map.Entry<String, Long>> getAccountsDeposits(long userId){
        return statisticsService.getAccountsDeposits(userId);
    }

    /*
    사용자 나이대 별로, 평균 예치금을 출력하시오
    IN : 없음
    OUT : List 형태[연령대(10, 20, 30 등등), 예치금]
     */
    @ApiOperation(value = "사용자 나이대 별로, 평균 예치금을 출력하시오")
    @GetMapping(value = "/average-deposit")
    public List<Map.Entry<String, Long>> getAgeAverageDeposits(){
        return statisticsService.getAgeAverageDeposits();
    }

    /*
    년도를 입력받아, 해당년도의 예치금 총액을 출력하시오
    IN : 년도
    OUT : 예치금 총액
     */
    @ApiOperation(value = "년도를 입력받아, 해당년도의 예치금 총액을 출력하시오. (년도 입력 포맷 yyyy)")
    @GetMapping(value = "/sum-deposit")
    public long getYearSumDeposits(int year){
        return statisticsService.getYearSumDeposits(year);
    }

    /*
     기간을 입력받아, 돈을 많이 예치한 사용자 순으로 정렬해서 출력하시오.
     ** 사용자 본의 계좌는 모두 합산합니다.
     ** 기간에는 시작일과 종료일이 포함되며, 해당 기간내의 입금액/출금액만이 계산됩니다.
     IN : 시작일, 종료일
     OUT : List 형태[사용자ID, 이름, 예치금]
     */
    @ApiOperation(value = "기간을 입력받아, 돈을 많이 예치한 사용자 순으로 정렬해서 출력하시오. (날짜 입력 포맷 yyyyMMdd)")
    @GetMapping(value = "/userlist-many-deposit-during-date")
    public List<UserDepositDto> getUserListManyDepositDuringDate(@DateTimeFormat(pattern = "yyyyMMdd")LocalDate start, @DateTimeFormat(pattern = "yyyyMMdd")LocalDate end){

        return statisticsService.getUserListManyDepositDuringDate(start,end);


    }

}