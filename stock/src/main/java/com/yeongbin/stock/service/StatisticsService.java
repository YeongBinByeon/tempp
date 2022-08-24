package com.yeongbin.stock.service;

import com.yeongbin.stock.entity.Account;
import com.yeongbin.stock.entity.AccountTransactionDetail;
import com.yeongbin.stock.entity.User;
import com.yeongbin.stock.repository.AccountRepository;
import com.yeongbin.stock.repository.AccountTransactionDetailRepository;
import com.yeongbin.stock.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log
@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountTransactionDetailRepository accountTransactionDetailRepository;

    public List<Map.Entry<String, Long>> getAccountsDeposits(long userId){
        List<Account> accountList = accountRepository.findAllByUserId(userId);
        List<Map.Entry<String, Long>> responseList = new ArrayList<>();
        accountList.forEach(a->{
            long deposit = 0;

            for( AccountTransactionDetail ad : a.getAccountTransactionDetailList()){
                if("Y".equalsIgnoreCase(ad.getIsDepositOperation())){
                    deposit += ad.getDepositMoney();
                }
                else if("N".equalsIgnoreCase(ad.getIsDepositOperation())){
                    deposit -= ad.getDepositMoney();
                }
                else{
                    log.warning("허용되지 않은 값 입력됨");
                }
            }
            responseList.add(new AbstractMap.SimpleEntry<String,Long>(a.getAccountNumber(), deposit));
        });
        return responseList;
    }

    public List<Map.Entry<String, Long>> getAgeAverageDeposits() {

        List<Map.Entry<String, Long>> responseList = new ArrayList<>();

        for (int i = 10; i <= 90; i = i + 10) {
            int min = 1 + i;
            int max = 10 + i;

            List<User> userList = userRepository.findByRangeAge(min, max);
            System.out.println(userList);

            int manNum = userList.size();

            long deposit = 0;
            for (User user : userList) {
                List<Map.Entry<String, Long>> valList = getAccountsDeposits(user.getId());
                for (Map.Entry<String, Long> val : valList) {
                    deposit += val.getValue();
                }
            }
            long averageDeposit = manNum == 0 ? 0 : deposit / manNum;
            //System.out.println(min + "~" + max + " 평균 금액 : " + averageDeposit);
            responseList.add(new AbstractMap.SimpleEntry<String, Long>("연령대 " + min + "~" + max
                    , averageDeposit));
        }
        return responseList;
    }

    public Long getYearSumDeposits(int year) {
        List<AccountTransactionDetail> accountTransactionDetailList =
                accountTransactionDetailRepository.findByYear(year);
        long deposit = 0;
        for( AccountTransactionDetail ad : accountTransactionDetailList){
            if("Y".equalsIgnoreCase(ad.getIsDepositOperation())){
                deposit += ad.getDepositMoney();
            }
            else if("N".equalsIgnoreCase(ad.getIsDepositOperation())){
                deposit -= ad.getDepositMoney();
            }
            else{
                log.warning("허용되지 않은 값 입력됨");
            }
        }
        return deposit;
    }
}
/*
long deposit = 0;

            for( AccountTransactionDetail ad : a.getAccountTransactionDetailList()){
                if("Y".equalsIgnoreCase(ad.getIsDepositOperation())){
                    deposit += ad.getDepositMoney();
                }
                else if("N".equalsIgnoreCase(ad.getIsDepositOperation())){
                    deposit -= ad.getDepositMoney();
                }
                else{
                    log.warning("허용되지 않은 값 입력됨");
                }
            }
 */