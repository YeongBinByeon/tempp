package com.yeongbin.stock.controller;

import com.yeongbin.stock.dto.AccountRegisterDto;
import com.yeongbin.stock.dto.AccountTransactionDetailRegisterDto;
import com.yeongbin.stock.entity.Account;
import com.yeongbin.stock.entity.AccountTransactionDetail;
import com.yeongbin.stock.service.AccountService;
import com.yeongbin.stock.service.AccountTransactionDetailService;
import com.yeongbin.stock.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;

@Api(tags = {"3. 계좌내역 추가/목록 API"})
@RestController
@RequiredArgsConstructor
public class AccountTransactionDetailController {
    private final ModelMapper modelMapper;
    private final AccountService accountService;
    private final AccountTransactionDetailService accountTransactionDetailService;

    @PostMapping(value = "/account/history")
    public AccountTransactionDetailRegisterDto registerAccountTransactionDetail(@Valid AccountTransactionDetailRegisterDto accountTransactionDetailRegisterDto
            , BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new ValidationException("AccountTransactionDetailRegisterDto Validation Error");
        }
        AccountTransactionDetail accountTransactionDetail = accountTransactionDetailService.registerAccountTransactionDetail(accountTransactionDetailRegisterDto);

        AccountTransactionDetailRegisterDto responseAccountTransactionDetailRegisterDto = new AccountTransactionDetailRegisterDto(
                accountTransactionDetail.getId(),
                accountTransactionDetail.getAccount().getAccountNumber(),
                accountTransactionDetail.getIsDepositOperation(),
                accountTransactionDetail.getDepositMoney(),
                accountTransactionDetail.getDepositDate());

        return responseAccountTransactionDetailRegisterDto;
    }

    @GetMapping(value = "/account/history")
    public List<AccountTransactionDetailRegisterDto> getAllAccountTransactionDetailList(){
        List<AccountTransactionDetail> allAccountTransactionDetailList = accountTransactionDetailService.getAllAccountTransactionDetailList();
        List<AccountTransactionDetailRegisterDto> accountTransactionDetailRegisterDtoList = new ArrayList<>();
        allAccountTransactionDetailList.forEach( accountTransactionDetail-> {
            AccountTransactionDetailRegisterDto accountTransactionDetailRegisterDto =
                    modelMapper.map(accountTransactionDetail, AccountTransactionDetailRegisterDto.class);
            accountTransactionDetailRegisterDto.setAccountId(accountTransactionDetail.getAccount().getAccountNumber());
            accountTransactionDetailRegisterDtoList.add(accountTransactionDetailRegisterDto);
        });
        return accountTransactionDetailRegisterDtoList;
    }
}
