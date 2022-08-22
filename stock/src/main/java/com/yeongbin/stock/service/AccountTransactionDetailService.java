package com.yeongbin.stock.service;

import com.yeongbin.stock.dto.AccountRegisterDto;
import com.yeongbin.stock.dto.AccountTransactionDetailRegisterDto;
import com.yeongbin.stock.entity.Account;
import com.yeongbin.stock.entity.AccountTransactionDetail;
import com.yeongbin.stock.entity.User;
import com.yeongbin.stock.repository.AccountRepository;
import com.yeongbin.stock.repository.AccountTransactionDetailRepository;
import com.yeongbin.stock.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AccountTransactionDetailService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountTransactionDetailRepository accountTransactionDetailRepository;

    public AccountTransactionDetail registerAccountTransactionDetail(AccountTransactionDetailRegisterDto accountTransactionDetailRegisterDto){
        Optional<Account> account = accountRepository.findById(accountTransactionDetailRegisterDto.getAccountId());
        if(!account.isPresent()){
            throw new EntityNotFoundException("존재하지 않는 Account에 거래 업무를 시도하였습니다.");
        }
        AccountTransactionDetail accountTransactionDetail =
                new AccountTransactionDetail(account.get(),
                        accountTransactionDetailRegisterDto.getIsDepositOperation(),
                        accountTransactionDetailRegisterDto.getDepositMoney(),
                        LocalDate.now());
        return accountTransactionDetailRepository.save(accountTransactionDetail);
    }

    public List<AccountTransactionDetail> getAllAccountTransactionDetailList(){
        return accountTransactionDetailRepository.findAll();
    }
}
