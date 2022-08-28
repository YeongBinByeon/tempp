package com.yeongbin.stock.service;

import com.yeongbin.stock.dto.UserRegisterRequestDto;
import com.yeongbin.stock.entity.Account;
import com.yeongbin.stock.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {
    @Autowired
    UserService userService;

    @DisplayName("유저 등록 성공 테스트")
    @Test
    void registerUserSuccessTest() {
        User user =
                new User("name", 10, LocalDate.now());
        User registeredUser = userService.registerUser(user);
        Assertions.assertEquals(user.getName(), registeredUser.getName());
        Assertions.assertEquals(user.getAge(), registeredUser.getAge());
        Assertions.assertEquals(user.getRegisteredDate(), registeredUser.getRegisteredDate());
    }

    @DisplayName("전체 유저 조회 성공 테스트")
    @Test
    void getAllUserListSuccessTest() {
        List<User> userList = userService.getAllUserList();
        Assertions.assertEquals(userList.size(),20);
    }
}