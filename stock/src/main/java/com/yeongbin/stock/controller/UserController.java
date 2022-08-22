package com.yeongbin.stock.controller;

import com.yeongbin.stock.dto.UserRegisterDto;
import com.yeongbin.stock.entity.User;
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
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    @PostMapping(value = "/user")
    public UserRegisterDto registerUser(@Valid UserRegisterDto userRegisterDto
            ,BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new ValidationException("UserRegisterDto Validation Error");
        }
        User user = modelMapper.map(userRegisterDto, User.class);
        user.setRegisteredDate(LocalDate.now());
        User registeredUser = userService.registerUser(user);

        UserRegisterDto registeredDto = modelMapper.map(registeredUser, UserRegisterDto.class);
        return registeredDto;
    }

    @GetMapping(value = "/user")
    public List<UserRegisterDto> getAllUserList(){
        List<User> allUserList = userService.getAllUserList();
        List<UserRegisterDto> userRegisterDtoList = new ArrayList<>();
        allUserList.forEach( user-> {
            userRegisterDtoList.add(modelMapper.map(user, UserRegisterDto.class));
        });
        return userRegisterDtoList;
    }


}
