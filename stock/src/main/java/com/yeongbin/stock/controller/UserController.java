package com.yeongbin.stock.controller;

import com.yeongbin.stock.dto.UserRegisterResponseDto;
import com.yeongbin.stock.dto.UserRegisterRequestDto;
import com.yeongbin.stock.entity.User;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Api(tags = {"1. 사용자 추가/목록 API"})
@RestController
@RequiredArgsConstructor
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    @PostMapping(value = "/user")
    public UserRegisterResponseDto registerUser(@Valid UserRegisterRequestDto userRegisterRequestDto
            , BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new ValidationException("UserRegisterDto Validation Error");
        }
        User user = modelMapper.map(userRegisterRequestDto, User.class);
        user.setRegisteredDate(LocalDate.now());
        User registeredUser = userService.registerUser(user);

        UserRegisterResponseDto registerResponseDto = modelMapper.map(registeredUser, UserRegisterResponseDto.class);
        return registerResponseDto;
    }

    @GetMapping(value = "/user")
    public List<UserRegisterResponseDto> getAllUserList(){
        List<User> allUserList = userService.getAllUserList();
        List<UserRegisterResponseDto> userRegisterResponseDtoList = new ArrayList<>();
        allUserList.forEach( user-> {
            userRegisterResponseDtoList.add(modelMapper.map(user, UserRegisterResponseDto.class));
        });
        return userRegisterResponseDtoList;
    }


}
