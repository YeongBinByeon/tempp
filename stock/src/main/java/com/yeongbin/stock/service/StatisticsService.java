package com.yeongbin.stock.service;

import com.yeongbin.stock.dto.UserDepositDto;
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
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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

    public List<UserDepositDto> getUserListManyDepositDuringDate(LocalDate start, LocalDate end) {

        List<AccountTransactionDetail> accountTransactionDetailList =
                accountTransactionDetailRepository.findByDateDuringRange(start,
                        end);

        // accountIdDeposit : 계좌별 예치금
        HashMap<String, Long> accountIdDeposit = new HashMap<>();
        for( AccountTransactionDetail ad : accountTransactionDetailList){

            if("Y".equalsIgnoreCase(ad.getIsDepositOperation())){
                accountIdDeposit.put(ad.getAccount().getAccountNumber(),
                        accountIdDeposit.getOrDefault(ad.getAccount(), 0L) + ad.getDepositMoney());
            }
            else if("N".equalsIgnoreCase(ad.getIsDepositOperation())){
                accountIdDeposit.put(ad.getAccount().getAccountNumber(),
                        accountIdDeposit.getOrDefault(ad.getAccount(), 0L) - ad.getDepositMoney());
            }
            else{
                log.warning("허용되지 않은 값 입력됨");
            }
        }
        System.out.println(accountIdDeposit.toString());



        // {1 : 30000, 2 : 40000 .... }
        // userIdDeposit : 유저별 예치금
        HashMap<Long, Long> userIdDeposit = new HashMap<>();
        // {1000-06=35600, 1000-07=76100, 1000-08=475900, 1000-09=-886600, 1000-02=788100, 1000-46=438800,
        List<Account> accountList  = accountRepository.findAll();
        accountList.forEach( a ->{
            // 계좌 리스트 전체를 가져와서 지정한 기간동안 거래내역이 없는 계좌는 로직 실행 안 시키도록 하기 위함
            if(accountIdDeposit.keySet().contains(a.getAccountNumber())) {
                userIdDeposit.put(a.getUser().getId(),
                        userIdDeposit.getOrDefault(a.getUser().getId(), 0L) + accountIdDeposit.get(a.getAccountNumber()));
            }

        });
        System.out.println(userIdDeposit.toString());


        // userIdUserNameMap : userId, userName
        // 유저 ID별 UserName 구성하기 위함
        Map<Long, String> userIdUserNameMap = new HashMap<>();
        List<User> userList = userRepository.findAll();
        userList.forEach(u->{
            userIdUserNameMap.put(u.getId(), u.getName());
        });


        // 예치금 순으로 내림차순 정렬
        List<Map.Entry<Long, Long>> entries = userIdDeposit.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());

        // Response 하기 위한 Dto List 생성
        List<UserDepositDto> userDepositDtoList = new ArrayList<>();
        for (Map.Entry<Long, Long> entry : entries) {
            System.out.println("Key: " + entry.getKey() + ", "
                    + "Value: " + entry.getValue());
            userDepositDtoList.add(new UserDepositDto(entry.getKey(), userIdUserNameMap.get(entry.getKey()), entry.getValue()));
        }
        return userDepositDtoList;

    }
}
