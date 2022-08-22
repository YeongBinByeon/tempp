package com.yeongbin.stock.controller;

import com.yeongbin.stock.dto.AccountRegisterDto;
import com.yeongbin.stock.dto.UserRegisterDto;
import com.yeongbin.stock.entity.Account;
import com.yeongbin.stock.entity.User;
import com.yeongbin.stock.service.AccountService;
import com.yeongbin.stock.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final ModelMapper modelMapper;
    private final AccountService accountService;

    @PostMapping(value = "/account")
    public AccountRegisterDto registerAccount(@Valid AccountRegisterDto accountRegisterDto
            , BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new ValidationException("AccountRegisterDto Validation Error");
        }
        Account account = accountService.registerAccount(accountRegisterDto);

        AccountRegisterDto responseAccountRegisterdDto = new AccountRegisterDto(account.getAccountNumber(), account.getUser().getId());

        return responseAccountRegisterdDto;
    }

    @GetMapping(value = "/account")
    public List<AccountRegisterDto> getAllAccountList(){
        List<Account> allAccountList = accountService.getAllAccountList();
        List<AccountRegisterDto> accountRegisterDtoList = new ArrayList<>();
        allAccountList.forEach( account-> {
            accountRegisterDtoList.add(modelMapper.map(account, AccountRegisterDto.class));
        });
        return accountRegisterDtoList;
    }
}
