package com.yeongbin.stock.service;

import com.yeongbin.stock.dto.AccountRegisterDto;
import com.yeongbin.stock.entity.Account;
import com.yeongbin.stock.entity.User;
import com.yeongbin.stock.repository.AccountRepository;
import com.yeongbin.stock.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

    public Account registerAccount(AccountRegisterDto accountRegisterDto){
        Optional<User> user = userRepository.findById(accountRegisterDto.getUserId());
        if(!user.isPresent()){
            throw new EntityNotFoundException("존재하지 않는 User의 계좌 생성을 시도하였습니다.");
        }
        Account account = new Account(accountRegisterDto.getAccountNumber(), user.get());
        return accountRepository.save(account);
    }

    public List<Account> getAllAccountList(){
        return accountRepository.findAll();
    }
}
